package io.daobab.demo.base

import io.daobab.model.Entity
import io.daobab.model.FlatPlate
import io.daobab.model.Plate
import io.daobab.result.FlatPlates
import io.daobab.target.buffer.single.Plates
import java.util.stream.Collectors

interface ToTableConverter {
    fun <V> resultToTable(obj: V?): String? {
        if (obj == null) return "null"
        return if (obj is Entity) {
            entityToTable(obj as Entity)
        } else if (obj is Plate) {
            getPlateRow(obj as Plate)
        } else if (obj is FlatPlate) {
            getFlatPlateRow(obj as FlatPlate)
        } else if (obj is Plates) {
            PlatesToTable(obj as Plates)
        } else if (obj is FlatPlates) {
            flatPlatesToTable(obj as FlatPlates)
        } else if (obj is Collection<*>) {
            val collection = obj as Collection<*>
            if (collection.isEmpty() == false && collection.iterator().next() is FlatPlate) {
                flatsToTable(collection as Collection<FlatPlate>)
            } else if (collection.isEmpty() == false && collection.iterator().next() is Entity) {
                entitiesToTable(collection as Collection<Entity>)
            } else {
                collection.stream().map{it.toString()}.collect(Collectors.joining("\n"))
//                cellsToTable<Any>(collection)
            }
        } else {
            getCellRow<V>(obj).toString()
        }

//         return "";
    }

    private fun entityToTable(obj: Entity): String {
        val table = StringBuffer()
        table.append(OPEN_TABLE)
            .append(getHeader(obj))
            .append(getRow(obj))
            .append(CLOSE_TABLE)
        return table.toString()
    }

    private fun PlatesToTable(objects: Plates): String {
        if (objects.isEmpty()) return ""
        val table = StringBuffer()
        table.append(OPEN_TABLE)
            .append(getHeader(objects.iterator().next()))
        for (obj in objects) {
            table.append(getPlateRow(obj))
        }
        table.append(CLOSE_TABLE)
        return table.toString()
    }

    private fun flatPlatesToTable(objects: FlatPlates): String {
        if (objects.isEmpty()) return ""
        val table = StringBuffer()
        table.append(OPEN_TABLE)
            .append(getHeader(objects.iterator().next()))
        for (obj in objects) {
            table.append(getFlatPlateRow(obj))
        }
        table.append(CLOSE_TABLE)
        return table.toString()
    }

    private fun entitiesToTable(objects: Collection<Entity>): String {
        if (objects.isEmpty()) return ""
        val table = StringBuffer()
        table.append(OPEN_TABLE)
            .append(getHeader(objects.iterator().next()))
        for (obj in objects) {
            table.append(getRow(obj))
        }
        table.append(CLOSE_TABLE)
        return table.toString()
    }

    private fun flatsToTable(objects: Collection<FlatPlate>): String {
        if (objects.isEmpty()) return ""
        val table = StringBuffer()
        table.append(OPEN_TABLE)
            .append(getHeader(objects.iterator().next()))
        for (obj in objects) {
            table.append(getRow(obj))
        }
        table.append(CLOSE_TABLE)
        return table.toString()
    }

    private fun <V> cellsToTable(objects: Collection<V>): String {
        if (objects.isEmpty()) return ""
        val table = StringBuffer()
//        table.append(OPEN_TABLE)
//            .append(getCellHeader<Any>(objects.iterator().next().javaClass.getSimpleName()))
        for (obj in objects) {
            table.append(getCellRow(obj))
        }
        table.append(CLOSE_TABLE)
        return table.toString()
    }

    private fun <V> getCellHeader(label: String): StringBuffer {
        val table = StringBuffer()
        table.append(OPEN_THEAD).append(OPEN_TR)
        table.append(OPEN_TH).append(label).append(CLOSE_TH)
        table.append(CLOSE_TR).append(CLOSE_THEAD)
        return table
    }

    private fun getHeader(obj: Entity): StringBuffer {
        val table = StringBuffer()
        table.append(OPEN_THEAD).append(OPEN_TR)
        for (col in obj.columns()) {
            table.append(OPEN_TH).append(col.column.columnName).append(CLOSE_TH)
        }
        table.append(CLOSE_TR).append(CLOSE_THEAD)
        return table
    }

    private fun getHeader(obj: FlatPlate): StringBuffer {
        val table = StringBuffer()
        table.append(OPEN_THEAD).append(OPEN_TR)
        for (col in obj.keys) {
            table.append(OPEN_TH).append(col).append(CLOSE_TH)
        }
        table.append(CLOSE_TR).append(CLOSE_THEAD)
        return table
    }

    private fun <V> getCellRow(obj: V): StringBuffer {
        val table = StringBuffer()
        table.append(OPEN_TBODY).append(OPEN_TR)
        table.append(OPEN_TD).append(obj).append(CLOSE_TD)
        table.append(CLOSE_TR).append(CLOSE_TBODY)
        return table
    }

    private fun getRow(obj: Entity): StringBuffer {
        val table = StringBuffer()
        table.append(OPEN_TBODY).append(OPEN_TR)
        for (col in obj.columns()) {
            val `val` = col.column.thisValue
            table.append(OPEN_TD).append(`val` ?: "").append(CLOSE_TD)
        }
        table.append(CLOSE_TR).append(CLOSE_TBODY)
        return table
    }

    private fun getRow(obj: FlatPlate): StringBuffer {
        val table = StringBuffer()
        table.append(OPEN_TBODY).append(OPEN_TR)
        for (col in obj.keys) {
            val `val` = obj[col]
            table.append(OPEN_TD).append(`val` ?: "").append(CLOSE_TD)
        }
        table.append(CLOSE_TR).append(CLOSE_TBODY)
        return table
    }

    private fun getHeader(obj: Plate): StringBuffer {
        val table = StringBuffer()
        table.append(OPEN_THEAD).append(OPEN_TR)
        for (key in obj.keys) {
            table.append(OPEN_TH).append(key).append(CLOSE_TH)
        }
        table.append(CLOSE_TR).append(CLOSE_THEAD)
        return table
    }

    private fun getPlateRow(obj: Plate): String {
        val table = StringBuffer()
        table.append(OPEN_TBODY).append(OPEN_TR)
        for (key in obj.keys) {
            table.append(OPEN_TH).append(obj.getValue<Any>(key) as String).append(CLOSE_TH)
        }
        table.append(CLOSE_TR).append(CLOSE_TBODY)
        return table.toString()
    }

    private fun getFlatPlateRow(obj: FlatPlate): String {
        val table = StringBuffer()
        table.append(OPEN_TBODY).append(OPEN_TR)
//        for (key in obj.columns()) {
//            table.append(OPEN_TH).append(obj.get(key.column) as String?).append(CLOSE_TH)
//        }
        table.append(CLOSE_TR).append(CLOSE_TBODY)
        return table.toString()
    }

    companion object {
        const val OPEN_TABLE = "<table class=\"table table-hover\">"
        const val OPEN_TH = "<th scope=\"col\">"
        const val OPEN_TD = "<td>"
        const val OPEN_TR = "<tr>"
        const val OPEN_THEAD = "<thead>"
        const val OPEN_TBODY = "<tbody>"
        const val CLOSE_TABLE = "</table>"
        const val CLOSE_TH = "</th>"
        const val CLOSE_TD = "</td>"
        const val CLOSE_TR = "</tr>"
        const val CLOSE_THEAD = "</thead>"
        const val CLOSE_TBODY = "</tbody>"
    }
}
