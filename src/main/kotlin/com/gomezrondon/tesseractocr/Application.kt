package com.gomezrondon.tesseractocr

import net.sourceforge.tess4j.Tesseract
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.io.File
import com.darkprograms.speech.translator.GoogleTranslate;
import org.springframework.context.ApplicationContext


fun main(args: Array<String>) {
	runApplication<Application>(*args)
}


@SpringBootApplication
class Application:CommandLineRunner {

	private var context: ApplicationContext? = null

	companion object {
		const val TRANSLATE = "translate"
		const val OCR = "ocr"
		const val ALL = "all"
	}
	//input output paste.png ocr | 4 parameter minimum
	//input output paste.png ocr translate
	//input output all ocr
	//input output all ocr translate
	override fun run(vararg args: String?) {

		//french.png ocr transale

		val argList = args.toMutableList()

		val inputFolder = argList[0]
		validateFolder(inputFolder)

		val outputFolder : String = argList[1]!!
		validateFolder(outputFolder)

		if (args.contains(ALL)) {
			val list = File(inputFolder).listFiles().filter { it.extension == "png" }
			list.forEach { imageFile ->
				applyProcess(imageFile, outputFolder, args)
			}
		} else {
			val imageFile = File("$inputFolder/" + argList[2])
			applyProcess(imageFile, outputFolder, args)
		}

	}

	private fun applyProcess(imageFile: File, outputFolder: String, args: Array<out String?>) {
		val fileName = getFileNameWithOutExt(imageFile)
		val outputTextFileName = applyOCR(outputFolder, fileName, args, imageFile)
		if (args.contains(TRANSLATE)) {
			translateFromFile(outputTextFileName, fileName, outputFolder)
		}
	}

	private fun applyOCR(outputFolder: String, fileName: String, args: Array<out String?>, imageFile: File): String {
		val outputTextFileName = "$outputFolder/$fileName.txt"
		val text = if (args.contains(OCR)) generateTextFromImage(imageFile) else ""
		writeToFile(outputTextFileName, text)
		return outputTextFileName
	}

	private fun validateFolder(inputFolder: String?) {
		if (!File(inputFolder).isDirectory) {
			println("Error, Folder $inputFolder does not exist!")
			System.exit(-1);
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

	private fun translateFromFile(inputFileText: String, inputFileName: String, outputFolder: String) {
		val output_trans ="$outputFolder/${inputFileName}_translated.txt"
		val inputText = File(inputFileText).readText()
		val translate = GoogleTranslate.translate("es", inputText)
		writeToFile(output_trans, translate)
	}

	private fun writeToFile(outputText: String, text: String) {
		File(outputText).writeText(text)
	}



}

