package com.gomezrondon.tesseractocr

import net.sourceforge.tess4j.Tesseract
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.io.File
import com.darkprograms.speech.translator.GoogleTranslate;



@SpringBootApplication
class Application:CommandLineRunner {

	companion object {
		const val TRANSLATE = "translate"
		const val OCR = "ocr"
	}

	override fun run(vararg args: String?) {

		//french.png ocr transale

		val argList = args.toMutableList()

		if (!File("out-text").isDirectory) {
			File("out-text").mkdir()
		}

		val imageFile = File("image/" + argList[0])
		val fileName = getFileNameWithOutExt(imageFile)

		val outputTextFileName = if (args.contains(OCR)) {
			val text = generateTextFromImage(imageFile)
			val outputTextFileName = "out-text/$fileName.txt"
			writeToFile(outputTextFileName, text)
			outputTextFileName
		} else {
			""
		}

		if (args.contains(TRANSLATE) && args.contains(OCR)) {
			translateFromFile(outputTextFileName, fileName)
		} else {
			translateFromFile("out-text/" +imageFile.name, fileName)
		}


	}

	private fun generateTextFromImage(imageFile: File): String {
		val tesseract = Tesseract()
		tesseract.setDatapath("""tessdata""")
		val text = tesseract.doOCR(imageFile)
		return text
	}

	private fun getFileNameWithOutExt(imageFile: File): String {
		return imageFile.nameWithoutExtension
	}

	private fun translateFromFile(inputFileText: String, inputFileName: String) {
		val output_trans ="out-text/${inputFileName}_tranlated.txt"
		val inputText = File(inputFileText).readText()
		val translate = GoogleTranslate.translate("es", inputText)
		writeToFile(output_trans, translate)
	}

	private fun writeToFile(outputText: String, text: String) {
		File(outputText).writeText(text)
	}


}

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}
