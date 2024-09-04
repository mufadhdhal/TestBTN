import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('https://www.btnproperti.co.id/tools/hitung-harga-properti')

WebUI.maximizeWindow()

WebUI.setText(findTestObject('hitungHarga/penghasilanTotal'), penghasilan)

WebUI.takeFullPageScreenshot()

WebUI.setText(findTestObject('hitungHarga/pengeluaran'), pengeluaran)

WebUI.takeFullPageScreenshot()

WebUI.selectOptionByValue(findTestObject('hitungHarga/jangkaWaktu'), tahun, false)

bulanInt = Integer.valueOf(tahun)

pengeluaranInt = Integer.valueOf(pengeluaran)

penghasilanInt = Integer.valueOf(penghasilan)

if (penghasilanInt <= pengeluaranInt) {
    WebUI.verifyElementText(findTestObject('hitungHarga/verifyWarning'), 'Isi kurang dari nilai sebelumnya')

    hitung = 0
} else {
	
    hitung = (((penghasilanInt - pengeluaranInt) * (bulanInt * 12)) / 3)
}

println(hitung)

WebUI.scrollToElement(findTestObject('hitungHarga/pengeluaran'), 0)

WebUI.verifyElementClickable(findTestObject('hitungHarga/buttonHitung'))

WebUI.click(findTestObject('hitungHarga/buttonHitung'))

WebUI.takeFullPageScreenshot()

harga = WebUI.getText(findTestObject('hitungHarga/harga'))

harga = harga.replace('Rp ', '').replace('.', '')

hargaInt = Integer.valueOf(harga)

println(hargaInt)

assert hitung == hargaInt

WebUI.closeBrowser()

