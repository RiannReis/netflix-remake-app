package co.tiagoaguiar.netflixremake.util

import android.util.Log
import java.io.BufferedInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.util.concurrent.Executors
import javax.net.ssl.HttpsURLConnection

class CategoryTask {

    fun execute(url: String){

        //nesse momento estamos utilizando a UI-Thread (1)
        val executor = Executors.newSingleThreadExecutor()

        executor.execute {
            try {
                //nesse momento estamos utilizando a NOVA-Thread [processo paralelo](2)
                val requestURL = URL(url) //abrir uma URL
                val urlConnection =
                    requestURL.openConnection() as HttpsURLConnection //abrir a conexão
                urlConnection.readTimeout = 2000    // tempo de leitura (2s)
                urlConnection.connectTimeout = 2000 // tempo de conexão (2s)

                val statusCode: Int = urlConnection.responseCode
                if(statusCode > 400){
                    throw IOException("Erro na comunicação com o servidor.")
                }

                val stream = urlConnection.inputStream //sequência de bytes

                //forma 1: simples e rápida
//                val jsonAsString = stream.bufferedReader().use { it.readText() }  //bytes -> string
//                Log.i("teste", jsonAsString)

                //forma 2: bytes -> String
                val buffer = BufferedInputStream(stream)
                val jsonAsString = toString(buffer)
                Log.i("teste", jsonAsString)


            } catch (e: IOException){
                Log.e("teste", e.message ?: "erro desconhecido", e)
            }

        }

    }

    private fun toString(stream: InputStream): String{
        val bytes = ByteArray(1024)
        val baos = ByteArrayOutputStream()
        var read: Int
        while (true){
            read = stream.read(bytes)
            if (read <= 0){
                break
            }
            baos.write(bytes, 0, read)
        }
        return String(baos.toByteArray())
    }

}