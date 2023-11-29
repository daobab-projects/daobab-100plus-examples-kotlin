package io.daobab.demo.dao.table

import io.daobab.demo.dao.column.FilmId
import io.daobab.demo.dao.column.Title
import io.daobab.demo.dao.column.Description
import io.daobab.demo.dao.column.ReleaseYear
import io.daobab.demo.dao.column.LanguageId
import io.daobab.demo.dao.column.OriginalLanguageId
import io.daobab.demo.dao.column.RentalDuration
import io.daobab.demo.dao.column.RentalRate
import io.daobab.demo.dao.column.Length
import io.daobab.demo.dao.column.ReplacementCost
import io.daobab.demo.dao.column.Rating
import io.daobab.demo.dao.column.SpecialFeatures
import io.daobab.demo.dao.column.LastUpdate

import io.daobab.creation.DaobabCache
import io.daobab.model.*
import java.time.LocalDateTime
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

open class Film : Table<Film>,
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

	constructor (): super()

	constructor (parameters: Map<String?, Any?>?): super(parameters)

	override fun columns(): List<TableColumn> = 
		DaobabCache.getTableColumns(this) {
			Arrays.asList(
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
}
	
override fun colID(): Column<Film, Int, FilmId<*, Int>> = colFilmId() as Column< Film, Int, FilmId<*, Int>>

	override fun hashCode() = Objects.hashCode(id)

	override fun equals(obj: Any?): Boolean {
		if (this === obj) return true
		if (obj == null) return false
		if (javaClass != obj.javaClass) return false
		val other: PrimaryKey<*, *, *> = obj as PrimaryKey<*, *, *>
		return id == other.id
	}

}
