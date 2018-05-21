package br.com.enzoteles.quickhelp.camera

import android.app.Activity
import android.app.AlertDialog
import android.app.Fragment
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.io.*
/**
 * Created by enzoteles on 14/03/18.
 */
open class HelpCamera: Fragment(){

    private val REQUEST_CAMERA = 0
    private val SELECT_FILE = 1
    private var userChoosenTask: String? = null

    fun selectImage() {
        val items = arrayOf<CharSequence>("Tirar Foto", "Escolher Foto", "Cancelar")

        val builder = AlertDialog.Builder(activity)
        builder.setTitle("Adicionar Foto!")
        builder.setItems(items) { dialog, item ->
            val result = HelpCameraUtility.checkPermission(activity)

            if (items[item] == "Tirar Foto") {
                userChoosenTask = "Tirar Foto"
                if (result)
                    cameraIntent()

            } else if (items[item] == "Escolher Foto") {
                userChoosenTask = "Escolher Foto"
                if (result)
                    galleryIntent()

            } else if (items[item] == "Cancelar") {
                dialog.dismiss()
            }
        }
        builder.show()
    }

    fun cameraIntent() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_CAMERA)
    }

    fun galleryIntent() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT//
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data)
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data!!)
        }
    }

    fun onCaptureImageResult(data: Intent) : Bitmap{
        val thumbnail = data.extras!!.get("data") as Bitmap
        val bytes = ByteArrayOutputStream()
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes)

        val destination = File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis().toString() + ".jpg")

        val fo: FileOutputStream
        try {
            destination.createNewFile()
            fo = FileOutputStream(destination)
            fo.write(bytes.toByteArray())
            fo.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return thumbnail

    }

    fun onSelectFromGalleryResult(data: Intent?):Bitmap {

        var bm: Bitmap? = null
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(activity.applicationContext.contentResolver, data.data)
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
        return bm!!
    }

}