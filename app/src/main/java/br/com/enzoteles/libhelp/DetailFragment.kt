package br.com.enzoteles.libhelp

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.enzoteles.quickhelp.log.HelpLog
import br.com.enzoteles.quickhelp.camera.HelpCamera
import kotlinx.android.synthetic.main.detail.*

class DetailFragment : HelpCamera(){

    private val REQUEST_CAMERA = 0
    private val SELECT_FILE = 1

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view = inflater!!.inflate(R.layout.detail, container, false)
        return view
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bt_photo.setOnClickListener {
            selectImage()
        }
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        HelpLog.info("${data}")

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                photo.setImageBitmap(onSelectFromGalleryResult(data))
            else if (requestCode == REQUEST_CAMERA)
                photo.setImageBitmap(onCaptureImageResult(data!!))
        }

    }



}














