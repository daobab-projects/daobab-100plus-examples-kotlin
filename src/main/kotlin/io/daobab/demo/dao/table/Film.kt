package io.daobab.demo.dao.table

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE
import com.fasterxml.jackson.annotation.JsonInclude
import io.daobab.clone.EntityDuplicator
import io.daobab.demo.dao.column.*
import io.daobab.model.PrimaryKey
import io.daobab.model.Table
import io.daobab.model.TableColumn
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, setterVisibility = NONE)
open class Film : Table(),
	FilmId<Film, Int>,
	Title<Film, String>,
	Description<Film, String?>,
	ReleaseYear<Film, LocalDate?>,
	LanguageId<Film, Int>,
	OriginalLanguageId<Film, Int?>,
	RentalDuration<Film, Int>,
	RentalRate<Film, BigDecimal>,
	Length<Film, Int?>,
	ReplacementCost<Film, BigDecimal>,
	Rating<Film, String?>,
	SpecialFeatures<Film, String?>,
	LastUpdate<Film, LocalDateTime>,

	PrimaryKey<Film,Int,FilmId<*, Int>>
	{

	override fun getEntityName() = "FILM"

	override fun columns() = 
		listOf(
			TableColumn(colFilmId()).primaryKey().size(16),
			TableColumn(colTitle()).size(255),
			TableColumn(colDescription()).size(1000000000),
			TableColumn(colReleaseYear()).size(10),
			TableColumn(colLanguageId()).size(8),
			TableColumn(colOriginalLanguageId()).size(8),
			TableColumn(colRentalDuration()).size(8),
			TableColumn(colRentalRate()).size(4).scale(2),
			TableColumn(colLength()).size(16),
			TableColumn(colReplacementCost()).size(5).scale(2),
			TableColumn(colRating()).size(5),
			TableColumn(colSpecialFeatures()).size(54),
			TableColumn(colLastUpdate()).size(26).scale(6)
		)

	override fun clone(): Film  {
		return EntityDuplicator.cloneEntity(this)
	}

	override fun colID() = colFilmId() 

	override fun hashCode() = Objects.hashCode(id)

    override fun equals(obj: Any?): Boolean {
        if (this === obj) return true
        if (obj == null) return false
        if (javaClass != obj.javaClass) return false
        val other: PrimaryKey<*, *, *> = obj as PrimaryKey<*, *, *>
        return id == other.id
    }



}
