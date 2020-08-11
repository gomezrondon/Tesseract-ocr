package com.gomezrondon.tesseractocr

import net.sourceforge.tess4j.Tesseract
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.io.File
import com.darkprograms.speech.translator.GoogleTranslate;



@SpringBootApplication
class Application:CommandLineRunner {
	override fun run(vararg args: String?) {
		val tesseract = Tesseract()
		tesseract.setDatapath("""tessdata""")

		val inputFile = File("image/"+args[0])
		val text = tesseract.doOCR(inputFile)

		if (!File("out-text").isDirectory) {
			File("out-text").mkdir()
		}
		val output ="out-text/"+inputFile.name.split(".")[0]+".txt"
		val output_trans ="out-text/"+inputFile.name.split(".")[0]+"_tranlated.txt"
		File(output).writeText(text)

		val translate = GoogleTranslate.translate("es", text)
		File(output_trans).writeText(translate)


	}
}

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}
