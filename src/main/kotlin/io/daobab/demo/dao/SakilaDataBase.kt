package io.daobab.demo.dao

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.daobab.demo.dao.procedure.SomeIn
import io.daobab.demo.dao.procedure.SomeOut
import io.daobab.demo.dao.table.*
import io.daobab.model.Entity
import io.daobab.target.database.DataBaseIdGeneratorSupplier
import io.daobab.target.database.DataBaseTarget
import io.daobab.target.database.connection.SqlProducer
import io.daobab.target.protection.Access
import jakarta.annotation.PostConstruct
import org.h2.tools.RunScript
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.sql.DataSource

@Component
class SakilaDataBase : DataBaseTarget(), SqlProducer {
    var recreate_database_on_start = true

    @Value("\${spring.datasource.url}")
    private val url: String? = null

    @Value("\${spring.datasource.username}")
    private val user: String? = null

    @Value("\${spring.datasource.password}")
    private val pass: String? = null

    @Value("\${spring.datasource.driverClassName}")
    private val driverClassName: String? = null

    @Autowired
    private val rentalGenerator: RentalGenerator? = null
    override fun initDataSource(): DataSource {
        val config = HikariConfig()
        config.jdbcUrl = url
        config.username = user
        config.password = pass
        config.driverClassName = driverClassName
        config.schema = "PUBLIC"
        val db = HikariDataSource(config)
        if (recreate_database_on_start) {
            createDatabaseContent(db)
        }
        return db
    }

    @PostConstruct
    fun init() {
        accessProtector.setColumnAccess(tabActor.colLastName(), Access.NO_INSERT, Access.NO_UPDATE)
        showSql = true
    }

    override fun initTables(): List<Entity> =
         listOf(
            tabActor,
            tabAddress,
            tabCategory,
            tabCity,
            tabCountry,
            tabCustomer,
            tabFilm,
            tabFilmActor,
            tabFilmCategory,
            tabFilmText,
            tabInventory,
            tabLanguage,
            tabPayment,
            tabRental,
            tabStaff,
            tabStore
        )


    private fun createDatabaseContent(ds: DataSource) {
        try {
            val con = ds.connection
            RunScript.execute(
                con, BufferedReader(InputStreamReader(ClassPathResource("schema.sql").inputStream))
            )
            RunScript.execute(
                con, BufferedReader(InputStreamReader(ClassPathResource("data.sql").inputStream))
            )
        } catch (e: Exception) {
            e.printStackTrace()
            throw RuntimeException("Database initialize script error")
        }
    }

    fun test(`in`: SomeIn): SomeOut {
        return callProcedure("test", `in`, SomeOut())
    }

    override fun <E : Entity> getPrimaryKeyGenerator(entity: E): DataBaseIdGeneratorSupplier<*>? {
        return if (Rental::class.java == entity.javaClass) {
            rentalGenerator
        } else null
    }



    /**

     * Comment:null<br>

     * <pre>

     * <u> Name         Type           Size  DBName       DBType     Description </u>
     *  ActorId(PK)  Integer        16  ACTOR_ID     SMALLINT
     *  FirstName    String         45  FIRST_NAME   VARCHAR
     *  LastName     String         45  LAST_NAME    VARCHAR
     *  LastUpdate   LocalDateTime  26  LAST_UPDATE  TIMESTAMP
     * </pre>

     */
    val tabActor: Actor = Actor()
    /**
     * Comment:null<br>
     * <pre>
     * <u> Name           Type           Size  DBName       DBType     Description </u>
     *  Address        String         50  ADDRESS      VARCHAR
     *  Address2       String         50  ADDRESS2     VARCHAR
     *  AddressId(PK)  Integer        16  ADDRESS_ID   SMALLINT
     *  CityId         Integer        16  CITY_ID      SMALLINT
     *  District       String         20  DISTRICT     VARCHAR
     *  LastUpdate     LocalDateTime  26  LAST_UPDATE  TIMESTAMP
     *  Phone          String         20  PHONE        VARCHAR
     *  PostalCode     String         10  POSTAL_CODE  VARCHAR
     * </pre>
     */
    val tabAddress: Address = Address()
    /**

     * Comment:null<br>

     * <pre>

     * <u> Name            Type           Size  DBName       DBType     Description </u>
     *  CategoryId(PK)  Integer        8   CATEGORY_ID  TINYINT
     *  LastUpdate      LocalDateTime  26  LAST_UPDATE  TIMESTAMP
     *  Name            String         25  NAME         VARCHAR
     * </pre>

     */
    val tabCategory: Category = Category()
    /**

     * Comment:null<br>

     * <pre>

     * <u> Name        Type           Size  DBName       DBType     Description </u>
     *  City        String         50  CITY         VARCHAR
     *  CityId(PK)  Integer        16  CITY_ID      SMALLINT
     *  CountryId   Integer        16  COUNTRY_ID   SMALLINT
     *  LastUpdate  LocalDateTime  26  LAST_UPDATE  TIMESTAMP
     * </pre>

     */
    val tabCity: City = City()
    /**

     * Comment:null<br>

     * <pre>

     * <u> Name           Type           Size  DBName       DBType     Description </u>
     *  Country        String         50  COUNTRY      VARCHAR
     *  CountryId(PK)  Integer        16  COUNTRY_ID   SMALLINT
     *  LastUpdate     LocalDateTime  26  LAST_UPDATE  TIMESTAMP
     * </pre>

     */
    val tabCountry: Country = Country()
    /***
//     * Comment:null<br>
//     * <table>
       Name            Type           Size  DBName       DBType     Description
       Active          Boolean        1   ACTIVE       BOOLEAN</br>
       AddressId       Integer        16  ADDRESS_ID   SMALLINT</br>
       CreateDate      LocalDateTime  26  CREATE_DATE  TIMESTAMP</br>
       CustomerId(PK)  Integer        16  CUSTOMER_ID  SMALLINT</br>
       Email           String         50  EMAIL        VARCHAR</br>
       FirstName       String         45  FIRST_NAME   VARCHAR</br>
       LastName        String         45  LAST_NAME    VARCHAR</br>
       LastUpdate      LocalDateTime  26  LAST_UPDATE  TIMESTAMP</br>
       StoreId         Integer        8   STORE_ID     TINYINT</br>
//     * </table>
     */
    val tabCustomer: Customer = Customer()
    /**

     * Comment:null<br>

     * <pre>

     * <u> Name                Type           Size        DBName                DBType     Description </u>
     *  Description         String         1000000000  DESCRIPTION           VARCHAR
     *  FilmId(PK)          Integer        16          FILM_ID               SMALLINT
     *  LanguageId          Integer        8           LANGUAGE_ID           TINYINT
     *  LastUpdate          LocalDateTime  26          LAST_UPDATE           TIMESTAMP
     *  Length              Integer        16          LENGTH                SMALLINT
     *  OriginalLanguageId  Integer        8           ORIGINAL_LANGUAGE_ID  TINYINT
     *  Rating              String         5           RATING                VARCHAR
     *  ReleaseYear         LocalDate      10          RELEASE_YEAR          DATE
     *  RentalDuration      Integer        8           RENTAL_DURATION       TINYINT
     *  RentalRate          BigDecimal     4           RENTAL_RATE           DECIMAL
     *  ReplacementCost     BigDecimal     5           REPLACEMENT_COST      DECIMAL
     *  SpecialFeatures     String         54          SPECIAL_FEATURES      VARCHAR
     *  Title               String         255         TITLE                 VARCHAR
     * </pre>

     */
    val tabFilm: Film = Film()
    /**

     * Comment:null<br>

     * <pre>

     * <u> Name         Type           Size  DBName       DBType     Description </u>
     *  ActorId(PK)  Integer        16  ACTOR_ID     SMALLINT
     *  FilmId(PK)   Integer        16  FILM_ID      SMALLINT
     *  LastUpdate   LocalDateTime  26  LAST_UPDATE  TIMESTAMP
     * </pre>

     */
    val tabFilmActor: FilmActor = FilmActor()
    /**

     * Comment:null<br>

     * <pre>

     * <u> Name            Type           Size  DBName       DBType     Description </u>
     *  CategoryId(PK)  Integer        8   CATEGORY_ID  TINYINT
     *  FilmId(PK)      Integer        16  FILM_ID      SMALLINT
     *  LastUpdate      LocalDateTime  26  LAST_UPDATE  TIMESTAMP
     * </pre>

     */
    val tabFilmCategory: FilmCategory = FilmCategory()
    /**

     * Comment:null<br>

     * <pre>

     * <u> Name         Type     Size        DBName       DBType    Description </u>
     *  Description  String   1000000000  DESCRIPTION  VARCHAR
     *  FilmId(PK)   Integer  16          FILM_ID      SMALLINT
     *  Title        String   255         TITLE        VARCHAR
     * </pre>

     */
    val tabFilmText: FilmText = FilmText()
    /**

     * Comment:null<br>

     * <pre>

     * <u> Name             Type           Size  DBName        DBType     Description </u>
     *  FilmId           Integer        16  FILM_ID       SMALLINT
     *  InventoryId(PK)  BigDecimal     32  INVENTORY_ID  INTEGER
     *  LastUpdate       LocalDateTime  26  LAST_UPDATE   TIMESTAMP
     *  StoreId          Integer        8   STORE_ID      TINYINT
     * </pre>

     */
    val tabInventory: Inventory = Inventory()
    /**

     * Comment:null<br>

     * <pre>

     * <u> Name            Type           Size  DBName       DBType     Description </u>
     *  LanguageId(PK)  Integer        8   LANGUAGE_ID  TINYINT
     *  LastUpdate      LocalDateTime  26  LAST_UPDATE  TIMESTAMP
     *  Name            String         20  NAME         VARCHAR
     * </pre>

     */
    val tabLanguage: Language = Language()
    /**

     * Comment:null<br>

     * <pre>

     * <u> Name           Type           Size  DBName        DBType     Description </u>
     *  Amount         BigDecimal     5   AMOUNT        DECIMAL
     *  CustomerId     Integer        16  CUSTOMER_ID   SMALLINT
     *  LastUpdate     LocalDateTime  26  LAST_UPDATE   TIMESTAMP
     *  PaymentDate    LocalDateTime  26  PAYMENT_DATE  TIMESTAMP
     *  PaymentId(PK)  Integer        16  PAYMENT_ID    SMALLINT
     *  RentalId       BigDecimal     32  RENTAL_ID     INTEGER
     *  StaffId        Integer        8   STAFF_ID      TINYINT
     * </pre>

     */
    val tabPayment: Payment = Payment()
    /**

     * Comment:null<br>

     * <pre>

     * <u> Name          Type           Size  DBName        DBType     Description </u>
     *  CustomerId    Integer        16  CUSTOMER_ID   SMALLINT
     *  InventoryId   BigDecimal     32  INVENTORY_ID  INTEGER
     *  LastUpdate    LocalDateTime  26  LAST_UPDATE   TIMESTAMP
     *  RentalDate    LocalDateTime  26  RENTAL_DATE   TIMESTAMP
     *  RentalId(PK)  BigDecimal     32  RENTAL_ID     INTEGER
     *  ReturnDate    LocalDateTime  26  RETURN_DATE   TIMESTAMP
     *  StaffId       Integer        8   STAFF_ID      TINYINT
     * </pre>

     */
    val tabRental: Rental = Rental()
    /**

     * Comment:null<br>

     * <pre>

     * <u> Name         Type           Size        DBName       DBType     Description </u>
     *  Active       Boolean        1           ACTIVE       BOOLEAN
     *  AddressId    Integer        16          ADDRESS_ID   SMALLINT
     *  Email        String         50          EMAIL        VARCHAR
     *  FirstName    String         45          FIRST_NAME   VARCHAR
     *  LastName     String         45          LAST_NAME    VARCHAR
     *  LastUpdate   LocalDateTime  26          LAST_UPDATE  TIMESTAMP
     *  Password     String         40          PASSWORD     VARCHAR
     *  Picture      byte[]         1000000000  PICTURE      VARBINARY
     *  StaffId(PK)  Integer        8           STAFF_ID     TINYINT
     *  StoreId      Integer        8           STORE_ID     TINYINT
     *  Username     String         16          USERNAME     VARCHAR
     * </pre>

     */
    val tabStaff: Staff = Staff()
    /**

     * Comment:null<br>

     * <pre>

     * <u> Name            Type           Size  DBName            DBType     Description </u>
     *  AddressId       Integer        16  ADDRESS_ID        SMALLINT
     *  LastUpdate      LocalDateTime  26  LAST_UPDATE       TIMESTAMP
     *  ManagerStaffId  Integer        8   MANAGER_STAFF_ID  TINYINT
     *  StoreId(PK)     Integer        8   STORE_ID          TINYINT
     * </pre>

     */
    val tabStore: Store = Store()


}
