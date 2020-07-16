package com.sstudio.talenta.mvp

import android.content.Context
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Toast
import com.itextpdf.text.*
import com.itextpdf.text.pdf.*
import com.itextpdf.xmp.XMPDateTimeFactory.getCurrentDateTime
import com.sstudio.talenta.Common
import com.sstudio.talenta.R
import com.sstudio.talenta.model.Result
import kotlinx.android.synthetic.main.activity_result.*
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class ToPDF(val context: Context, val result: Result) {
    companion object {
        val INPUT_EXTRA = "input_extra"
        val ID_EXTRA = "id_extra"
    }

    fun toPdf() {
        // Create output PDF
        val document = Document(PageSize.A4)
        val targetDir = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_DOCUMENTS
        )
        val sdfForFile = SimpleDateFormat("dd-MM-yy HH:mm:ss")
        val currentDateForFile = sdfForFile.format(Date())
        val targetFile = File(targetDir, "talenTA - ${result.name} - $currentDateForFile.pdf")
        targetFile.createNewFile()
        val writer = PdfWriter.getInstance(document, FileOutputStream(targetFile))

        document.open()
        val cb = writer.directContent

// Load existing PDF
        val reader = PdfReader(context.resources.openRawResource(R.raw.template))
        val page = writer.getImportedPage(reader, 1)
        // Copy first page of existing PDF into output PDF
        document.newPage()
        cb.addTemplate(page, 0f, 0f)

// Add your new data / text here
// for example...
        val idLabel = intArrayOf(54, 758)
        val id = intArrayOf(147, 758)
        val notesLabel = intArrayOf(54, 745)
        val notes = intArrayOf(147, 745)
        val name = intArrayOf(160, 733)
        val birth = intArrayOf(160, 718)
        val address = intArrayOf(160, 703)

        val study = intArrayOf(447, 733)
        val date = intArrayOf(447, 718)
        val purpose_test = intArrayOf(447, 703) //8170 220
        val data_inteligent = arrayOf(
            arrayOf(
                intArrayOf(274, 580),
                intArrayOf(299, 580),
                intArrayOf(324, 580),
                intArrayOf(349, 580),
                intArrayOf(374, 580),
                intArrayOf(399, 580)
            ),
            arrayOf(
                intArrayOf(274, 540),
                intArrayOf(299, 540),
                intArrayOf(324, 540),
                intArrayOf(349, 540),
                intArrayOf(374, 540),
                intArrayOf(399, 540)
            ),
            arrayOf(
                intArrayOf(274, 505),
                intArrayOf(299, 505),
                intArrayOf(324, 505),
                intArrayOf(349, 505),
                intArrayOf(374, 505),
                intArrayOf(399, 505)
            ),
            arrayOf(
                intArrayOf(274, 475),///////////////
                intArrayOf(299, 475),
                intArrayOf(324, 475),
                intArrayOf(349, 475),
                intArrayOf(374, 475),
                intArrayOf(399, 475)
            ),
            arrayOf(
                intArrayOf(274, 445),
                intArrayOf(299, 445),
                intArrayOf(324, 445),
                intArrayOf(349, 445),
                intArrayOf(374, 445),
                intArrayOf(399, 445)
            ),
            arrayOf(
                intArrayOf(274, 420),
                intArrayOf(299, 420),
                intArrayOf(324, 420),
                intArrayOf(349, 420),
                intArrayOf(374, 420),
                intArrayOf(399, 420)
            ),
            arrayOf(
                intArrayOf(274, 385),
                intArrayOf(299, 385),
                intArrayOf(324, 385),
                intArrayOf(349, 385),
                intArrayOf(374, 385),
                intArrayOf(399, 385)
            ),
            arrayOf(
                intArrayOf(274, 350),
                intArrayOf(299, 350),
                intArrayOf(324, 350),
                intArrayOf(349, 350),
                intArrayOf(374, 350),
                intArrayOf(399, 350)
            )
        )

        val data_work = arrayOf(
            arrayOf(
                intArrayOf(274, 275),
                intArrayOf(299, 275),
                intArrayOf(324, 275),
                intArrayOf(349, 275),
                intArrayOf(374, 275),
                intArrayOf(399, 275)
            ),
            arrayOf(
                intArrayOf(274, 255),
                intArrayOf(299, 255),
                intArrayOf(324, 255),
                intArrayOf(349, 255),
                intArrayOf(374, 255),
                intArrayOf(399, 255)
            ),
            arrayOf(
                intArrayOf(274, 225),
                intArrayOf(299, 225),
                intArrayOf(324, 225),
                intArrayOf(349, 225),
                intArrayOf(374, 225),
                intArrayOf(399, 225)
            ),
            arrayOf(
                intArrayOf(274, 200),
                intArrayOf(299, 200),
                intArrayOf(324, 200),
                intArrayOf(349, 200),
                intArrayOf(374, 200),
                intArrayOf(399, 200)
            ),
            arrayOf(
                intArrayOf(274, 165),
                intArrayOf(299, 165),
                intArrayOf(324, 165),
                intArrayOf(349, 165),
                intArrayOf(374, 165),
                intArrayOf(399, 165)
            )
        )

        val data_self = arrayOf(
            arrayOf(
                intArrayOf(274, 760),
                intArrayOf(299, 760),
                intArrayOf(324, 760),
                intArrayOf(349, 760),
                intArrayOf(374, 760),
                intArrayOf(399, 760)
            ),
            arrayOf(
                intArrayOf(274, 730),
                intArrayOf(299, 730),
                intArrayOf(324, 730),
                intArrayOf(349, 730),
                intArrayOf(374, 730),
                intArrayOf(399, 730)
            ),
            arrayOf(
                intArrayOf(274, 705),
                intArrayOf(299, 705),
                intArrayOf(324, 705),
                intArrayOf(349, 705),
                intArrayOf(374, 705),
                intArrayOf(399, 705)
            ),
            arrayOf(
                intArrayOf(274, 675),
                intArrayOf(299, 675),
                intArrayOf(324, 675),
                intArrayOf(349, 675),
                intArrayOf(374, 675),
                intArrayOf(399, 675)
            ),
            arrayOf(
                intArrayOf(274, 645),
                intArrayOf(299, 645),
                intArrayOf(324, 645),
                intArrayOf(349, 645),
                intArrayOf(374, 645),
                intArrayOf(399, 645)
            )
        )
        //id
        placeChunck(result.name, cb, name)
        placeChunck("No.Tes", cb, notesLabel)
        placeChunck("ID", cb, idLabel)
        placeChunck(": ${result.id}", cb, id)
        result.noTest?.let { placeChunck(": ${it}", cb, notes) }
        result.placeDateBirth?.let { placeChunck(it, cb, birth) }
        result.address?.let { placeChunck(it, cb, address) }
        result.study?.let { placeChunck(it, cb, study) }

        result.purposeTest?.let { placeChunck(it, cb, purpose_test) }
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val currentDate = sdf.format(Date())
        placeChunck(currentDate, cb, date)
        var x = 0
        var i = 0
        result.potencyValue?.let {
            for (j in 0 until 8) {
                x = getX(it, j)
                placeChunck("o", cb, data_inteligent[j][x])
            }

            for (j in 8 until 13) {
                x = getX(it, j)
                placeChunck("o", cb, data_work[i][x])
                i++
            }
        }

        i = 0
        val page2 = writer.getImportedPage(reader, 2)
        // Copy first page of existing PDF into output PDF
        document.newPage()
        result.potencyValue?.let {
            for (j in 13 until 18) {
                x = getX(it, j)
                placeChunck("o", cb, data_self[i][x])
                i++
            }
        }

        cb.addTemplate(page2, 0f, 0f)

        repeat(17) {
            document.add(Paragraph("\n"))
        }
        result.finalResult?.let {
            val predic =  findHighest(it)
            showConclusion(predic, document)
            Log.d("myTag", "final result = ${it[0].toString()}")
        }
        psikolog(document)
        document.close()
        Toast.makeText(context, "PDF telah tersimpan di document", Toast.LENGTH_SHORT).show()
    }

    private fun getX(it: DoubleArray, j: Int): Int{
        var x = 0
        when {
            it[j] == 0.0 -> {
                x = 0
            }
            it[j] == 0.2 -> {
                x = 1
            }
            it[j] == 0.4 -> {
                x = 2
            }
            it[j] == 0.6 -> {
                x = 3
            }
            it[j] == 0.8 -> {
                x = 4
            }
            it[j] == 1.0 -> {
                x = 5
            }
        }
        return x
    }

    private fun showConclusion(predic: DoubleArray, document: Document) {
        for (i in predic.indices) {
            when {
                predic[i] == 0.0 -> teknik(document)
                predic[i] == 1.0 -> bahasa(document)
                predic[i] == 2.0 -> sosial(document)
                predic[i] == 3.0 -> seni(document)
                predic[i] == 4.0 -> medical(document)
            }
        }
    }

    private fun findHighest(predic: Array<Double>): DoubleArray {
        var ss = doubleArrayOf()
        var j = predic[0]
        for (i in 1 until predic.size) {
            if (j < predic[i]) {
                j = predic[i]
            }
        }
        for (i in predic.indices) {
            if (j == predic[i]) {
                ss += i.toDouble()
            }
        }
        return ss
    }

    private fun psikolog(document: Document) {
        document.add(Paragraph("\n"))
        val titleFont = FontFactory.getFont(FontFactory.HELVETICA, 12.0f, Font.BOLD)
        var selectorTitle = FontSelector()
        selectorTitle.addFont(titleFont)

        val selectorNama = FontSelector()
        val namaFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12.0f, Font.BOLD)
        selectorNama.addFont(namaFont)
        val namaParagraph = Paragraph()
//        namaParagraph.tabSettings = TabSettings(180.0f)
        namaParagraph.alignment = Element.ALIGN_CENTER
        namaParagraph.add(selectorNama.process("Psikolog Pemeriksa\n\n\n\n"))
//        namaParagraph.add(Chunk.TABBING)
        namaParagraph.add(selectorNama.process("${Common.namePsycholog} \n"))
        namaParagraph.add(selectorNama.process("SIPP: ${Common.sipp}"))
        document.add(namaParagraph)
    }

    private fun teknik(document: Document) {

        val titleFont = FontFactory.getFont(FontFactory.HELVETICA, 12.0f, Font.BOLD)
        var selectorTitle = FontSelector()
        selectorTitle.addFont(titleFont)

        val subTitleFont = FontFactory.getFont(FontFactory.HELVETICA, 11.0f, Font.BOLD)
        var selectorSubTitle = FontSelector()
        selectorSubTitle.addFont(subTitleFont)

        val workTitleFont = FontFactory.getFont(FontFactory.HELVETICA, 10.0f, Font.BOLD)
        var selectorWorkTitle = FontSelector()
        selectorWorkTitle.addFont(workTitleFont)

        val regularFont = FontFactory.getFont(FontFactory.HELVETICA, 10.0f)
        var selectorRegular = FontSelector()
        selectorRegular.addFont(regularFont)

        val table = PdfPTable(2)
        table.setWidths(intArrayOf(1, 3))
        var cell = PdfPCell(selectorTitle.process("TEKNIK"))
        cell.colspan = 2
        table.addCell(cell)
        table.addCell(selectorSubTitle.process("Scientific"))
        table.addCell(selectorRegular.process(context.resources.getString(R.string.scientific)))

        cell = PdfPCell(selectorWorkTitle.process("Pekerjaan"))
        cell.colspan = 2
        table.addCell(cell)
        table.addCell(selectorRegular.process("Pria"))
        table.addCell(selectorRegular.process(context.resources.getString(R.string.scientificMan)))
        table.addCell(selectorRegular.process("Wanita"))
        table.addCell(selectorRegular.process(context.resources.getString(R.string.scientificWom)))

        table.addCell(selectorSubTitle.process("Computational"))
        table.addCell(selectorRegular.process(context.resources.getString(R.string.computational)))

        cell = PdfPCell(selectorWorkTitle.process("Pekerjaan"))
        cell.colspan = 2
        table.addCell(cell)
        table.addCell(selectorRegular.process("Pria"))
        table.addCell(selectorRegular.process(context.resources.getString(R.string.computationalMan)))
        table.addCell(selectorRegular.process("Wanita"))
        table.addCell(selectorRegular.process(context.resources.getString(R.string.computationalWom)))

        table.addCell(selectorSubTitle.process("Mechanical"))
        table.addCell(selectorRegular.process(context.resources.getString(R.string.mechanical)))

        cell = PdfPCell(selectorWorkTitle.process("Pekerjaan"))
        cell.colspan = 2
        table.addCell(cell)
        table.addCell(selectorRegular.process("Pria"))
        table.addCell(selectorRegular.process(context.resources.getString(R.string.mechanicalMan)))
        table.addCell(selectorRegular.process("Wanita"))
        table.addCell(selectorRegular.process(context.resources.getString(R.string.mechanicalWom)))

        table.addCell(selectorSubTitle.process("Outdoor"))
        table.addCell(selectorRegular.process(context.resources.getString(R.string.outdoor)))

        cell = PdfPCell(selectorWorkTitle.process("Pekerjaan"))
        cell.colspan = 2
        table.addCell(cell)
        table.addCell(selectorRegular.process("Pria"))
        table.addCell(selectorRegular.process(context.resources.getString(R.string.outdoorMan)))
        table.addCell(selectorRegular.process("Wanita"))
        table.addCell(selectorRegular.process(context.resources.getString(R.string.outdoorWom)))

        document.add(table)
    }

    private fun bahasa(document: Document) {

        val titleFont = FontFactory.getFont(FontFactory.HELVETICA, 12.0f, Font.BOLD)
        val selectorTitle = FontSelector()
        selectorTitle.addFont(titleFont)

        val subTitleFont = FontFactory.getFont(FontFactory.HELVETICA, 11.0f, Font.BOLD)
        val selectorSubTitle = FontSelector()
        selectorSubTitle.addFont(subTitleFont)

        val workTitleFont = FontFactory.getFont(FontFactory.HELVETICA, 10.0f, Font.BOLD)
        val selectorWorkTitle = FontSelector()
        selectorWorkTitle.addFont(workTitleFont)

        val regularFont = FontFactory.getFont(FontFactory.HELVETICA, 10.0f)
        val selectorRegular = FontSelector()
        selectorRegular.addFont(regularFont)

        val table = PdfPTable(2)
        table.setWidths(intArrayOf(1, 3))
        var cell = PdfPCell(selectorTitle.process("BAHASA"))
        cell.colspan = 2
        table.addCell(cell)
        table.addCell(selectorSubTitle.process("Literary"))
        table.addCell(selectorRegular.process(context.resources.getString(R.string.literary)))

        cell = PdfPCell(selectorWorkTitle.process("Pekerjaan"))
        cell.colspan = 2
        table.addCell(cell)
        table.addCell(selectorRegular.process("Pria"))
        table.addCell(selectorRegular.process(context.resources.getString(R.string.literaryMan)))
        table.addCell(selectorRegular.process("Wanita"))
        table.addCell(selectorRegular.process(context.resources.getString(R.string.literaryWom)))

        table.addCell(selectorSubTitle.process("Persuasive"))
        table.addCell(selectorRegular.process(context.resources.getString(R.string.persuasive)))

        cell = PdfPCell(selectorWorkTitle.process("Pekerjaan"))
        cell.colspan = 2
        table.addCell(cell)
        table.addCell(selectorRegular.process("Pria"))
        table.addCell(selectorRegular.process(context.resources.getString(R.string.persuasiveMan)))
        table.addCell(selectorRegular.process("Wanita"))
        table.addCell(selectorRegular.process(context.resources.getString(R.string.persuasivewom)))

        document.add(table)
    }

    private fun sosial(document: Document) {

        val titleFont = FontFactory.getFont(FontFactory.HELVETICA, 12.0f, Font.BOLD)
        var selectorTitle = FontSelector()
        selectorTitle.addFont(titleFont)

        val subTitleFont = FontFactory.getFont(FontFactory.HELVETICA, 11.0f, Font.BOLD)
        var selectorSubTitle = FontSelector()
        selectorSubTitle.addFont(subTitleFont)

        val workTitleFont = FontFactory.getFont(FontFactory.HELVETICA, 10.0f, Font.BOLD)
        var selectorWorkTitle = FontSelector()
        selectorWorkTitle.addFont(workTitleFont)

        val regularFont = FontFactory.getFont(FontFactory.HELVETICA, 10.0f)
        var selectorRegular = FontSelector()
        selectorRegular.addFont(regularFont)

        val table = PdfPTable(2)
        table.setWidths(intArrayOf(1, 3))
        var cell = PdfPCell(selectorTitle.process("SOSIAL"))
        cell.colspan = 2
        table.addCell(cell)
        table.addCell(selectorSubTitle.process("Clerical"))
        table.addCell(selectorRegular.process(context.resources.getString(R.string.clerical)))

        cell = PdfPCell(selectorWorkTitle.process("Pekerjaan"))
        cell.colspan = 2
        table.addCell(cell)
        table.addCell(selectorRegular.process("Pria"))
        table.addCell(selectorRegular.process(context.resources.getString(R.string.clericalMan)))
        table.addCell(selectorRegular.process("Wanita"))
        table.addCell(selectorRegular.process(context.resources.getString(R.string.clericalWom)))

        table.addCell(selectorSubTitle.process("Social \nServices"))
        table.addCell(selectorRegular.process(context.resources.getString(R.string.social_service)))

        cell = PdfPCell(selectorWorkTitle.process("Pekerjaan"))
        cell.colspan = 2
        table.addCell(cell)
        table.addCell(selectorRegular.process("Pria"))
        table.addCell(selectorRegular.process(context.resources.getString(R.string.social_serviceMan)))
        table.addCell(selectorRegular.process("Wanita"))
        table.addCell(selectorRegular.process(context.resources.getString(R.string.social_serviceWom)))

        table.addCell(selectorSubTitle.process("Practical"))
        table.addCell(selectorRegular.process(context.resources.getString(R.string.practical)))

        cell = PdfPCell(selectorWorkTitle.process("Pekerjaan"))
        cell.colspan = 2
        table.addCell(cell)
        table.addCell(selectorRegular.process("Pria"))
        table.addCell(selectorRegular.process(context.resources.getString(R.string.practicalMan)))
        table.addCell(selectorRegular.process("Wanita"))
        table.addCell(selectorRegular.process(context.resources.getString(R.string.practicalWom)))
        document.add(table)
    }

    private fun seni(document: Document) {

        val titleFont = FontFactory.getFont(FontFactory.HELVETICA, 12.0f, Font.BOLD)
        var selectorTitle = FontSelector()
        selectorTitle.addFont(titleFont)

        val subTitleFont = FontFactory.getFont(FontFactory.HELVETICA, 11.0f, Font.BOLD)
        var selectorSubTitle = FontSelector()
        selectorSubTitle.addFont(subTitleFont)

        val workTitleFont = FontFactory.getFont(FontFactory.HELVETICA, 10.0f, Font.BOLD)
        var selectorWorkTitle = FontSelector()
        selectorWorkTitle.addFont(workTitleFont)

        val regularFont = FontFactory.getFont(FontFactory.HELVETICA, 10.0f)
        var selectorRegular = FontSelector()
        selectorRegular.addFont(regularFont)

        val table = PdfPTable(2)
        table.setWidths(intArrayOf(1, 3))
        var cell = PdfPCell(selectorTitle.process("SENI"))
        cell.colspan = 2
        table.addCell(cell)
        table.addCell(selectorSubTitle.process("Musical"))
        table.addCell(selectorRegular.process(context.resources.getString(R.string.musical)))

        cell = PdfPCell(selectorWorkTitle.process("Pekerjaan"))
        cell.colspan = 2
        table.addCell(cell)
        table.addCell(selectorRegular.process("Pria"))
        table.addCell(selectorRegular.process(context.resources.getString(R.string.musicalMan)))
        table.addCell(selectorRegular.process("Wanita"))
        table.addCell(selectorRegular.process(context.resources.getString(R.string.musicalWom)))

        table.addCell(selectorSubTitle.process("Aesthetic"))
        table.addCell(selectorRegular.process(context.resources.getString(R.string.aesthetic)))

        cell = PdfPCell(selectorWorkTitle.process("Pekerjaan"))
        cell.colspan = 2
        table.addCell(cell)
        table.addCell(selectorRegular.process("Pria"))
        table.addCell(selectorRegular.process(context.resources.getString(R.string.aestheticMan)))
        table.addCell(selectorRegular.process("Wanita"))
        table.addCell(selectorRegular.process(context.resources.getString(R.string.aestheticWom)))
        document.add(table)
    }

    private fun medical(document: Document) {

        val titleFont = FontFactory.getFont(FontFactory.HELVETICA, 12.0f, Font.BOLD)
        var selectorTitle = FontSelector()
        selectorTitle.addFont(titleFont)

        val subTitleFont = FontFactory.getFont(FontFactory.HELVETICA, 11.0f, Font.BOLD)
        var selectorSubTitle = FontSelector()
        selectorSubTitle.addFont(subTitleFont)

        val workTitleFont = FontFactory.getFont(FontFactory.HELVETICA, 10.0f, Font.BOLD)
        var selectorWorkTitle = FontSelector()
        selectorWorkTitle.addFont(workTitleFont)

        val regularFont = FontFactory.getFont(FontFactory.HELVETICA, 10.0f)
        var selectorRegular = FontSelector()
        selectorRegular.addFont(regularFont)

        val table = PdfPTable(2)
        table.setWidths(intArrayOf(1, 3))
        var cell = PdfPCell(selectorTitle.process("MEDICAL"))
        cell.colspan = 2
        table.addCell(cell)
        table.addCell(selectorSubTitle.process("Medical"))
        table.addCell(selectorRegular.process(context.resources.getString(R.string.medical)))

        cell = PdfPCell(selectorWorkTitle.process("Pekerjaan"))
        cell.colspan = 2
        table.addCell(cell)
        table.addCell(selectorRegular.process("Pria"))
        table.addCell(selectorRegular.process(context.resources.getString(R.string.medicalMan)))
        table.addCell(selectorRegular.process("Wanita"))
        table.addCell(selectorRegular.process(context.resources.getString(R.string.medicalWom)))

        document.add(table)
    }

    fun placeChunck(text: String, cb: PdfContentByte, xy: IntArray) {

        val bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
        cb.saveState();
        cb.beginText();
        cb.moveText(xy[0].toFloat(), xy[1].toFloat());
        cb.setFontAndSize(bf, 12F);
        cb.showText(text);
        cb.endText();
        cb.restoreState();
    }
}