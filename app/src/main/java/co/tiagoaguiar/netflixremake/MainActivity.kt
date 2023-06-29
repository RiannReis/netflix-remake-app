package co.tiagoaguiar.netflixremake

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    //disparado toda vez que ligamos nossa tela (activity)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("teste", "onCreate")
    }
    //disparado quando a atividade de fato inicializa
    override fun onStart(){
        super.onStart()
        Log.i("teste", "onStart")
    }
    //disparado quando a interface está preparada para receber eventos de touch
    override fun onResume(){
        super.onResume()
        Log.i("teste", "onResume")
    }
    //disparado quando há algum evento que faz com que a atividade seja pausada
    override fun onPause() {
        super.onPause()
        Log.i("teste", "onPause")
    }
    //disparado quando a atividade começa a entrar em processo de desligamento
    override fun onStop(){
        super.onStop()
        Log.i("teste", "onStop")
    }
    //disparado quando a atividade vai sendo destruída realmente
    override fun onDestroy(){
        super.onDestroy()
        Log.i("teste", "onDestroy")
    }
}