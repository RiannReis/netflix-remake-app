package co.tiagoaguiar.netflixremake.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.util.Log
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.util.concurrent.Executors
import javax.net.ssl.HttpsURLConnection

class DownloadImageTask(private val callback: Callback) {

    private val handler = Handler(Looper.getMainLooper())
    private val executor = Executors.newSingleThreadExecutor()

    interface Callback {
        fun onResult(bitmap: Bitmap)
    }

    fun execute(url: String) {
        executor.execute {
            var urlConnection: HttpsURLConnection? = null
            var stream: InputStream? = null

            try {
                val requestURL = URL(url) //abrir uma URL
                urlConnection =
                    requestURL.openConnection() as HttpsURLConnection //abrir a conexão
                urlConnection.readTimeout = 2000    // tempo de leitura (2s)
                urlConnection.connectTimeout = 2000 // tempo de conexão (2s)

                val statusCode: Int = urlConnection.responseCode
                if (statusCode > 400) {
                    throw IOException("Erro na comunicação com o servidor.")
                }

                stream = urlConnection.inputStream //sequência de bytes
                val btMap = BitmapFactory.decodeStream(stream)

                handler.post {
                    callback.onResult(btMap)  //UI-Thread
                }

            } catch (e: IOException) {
                val message = e.message ?: "erro desconhecido"
                Log.e("teste", message, e)
            } finally {
                urlConnection?.disconnect()
                stream?.close()
            }

        }

    }
}