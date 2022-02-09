/*
INFORMAÇÕES DA AMOSTRAGEM
Informar nome do técnico, equipamento utilizado, data e código da FT-14
> Preencher Técnico, Equipamento, Data e FT-14
> Mostra "bem-vindo"
> Vai para "Inseririnfos"

ATIVIDADE ANTERIOR: -
ATIVIDADE SUBSEQUENTE: InserirInfos
* */


package com.example.sapotec_dpr

import android.app.DatePickerDialog
// import android.content.DialogInterface
import android.content.Intent
// import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import java.text.SimpleDateFormat
import java.util.*
import android.media.MediaPlayer as MediaPlayer


class Tela2_InformacoesGerais : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela2_informacoesgerais)

        Log.i("\t-----", "----")
        Log.i("\tLIFE CYCLE 1st Activ", "onCreate()")
        /*
        Essa parte é a que preenche o Spinner (Lista suspensa) com o StringArray armazenado em Resources

        val Equipamentos = resources.getStringArray(R.array.Equipamentos)
        val spinner = findViewById<Spinner>(R.id.equipamento)
        if (spinner != null) {
            val adapter = ArrayAdapter(this, R.layout.spinner_item, Equipamentos)
            spinner.adapter = adapter
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        }

         */
    }

    override fun onStart() {
        super.onStart()
        // Obter variáveis pelo Intent da
        // 1a Atividade
        val responsavel = intent.getStringExtra("RESPONSAVEL")
        val ft14 = intent.getStringExtra("FT14")
        val data = intent.getStringExtra("DATA")
        val multiparametro = intent.getStringExtra("MULTIPARAMETRO")
        val turbidimetro = intent.getStringExtra("TURBIDIMETRO")
        val pHmetro = intent.getStringExtra("PHMETRO")

        // 2a Atividade
        // Multiparametro
        val odExt = intent.getStringExtra("OD_EXT")
        val ceExt = intent.getStringExtra("CE_EXT")
        val orpExt = intent.getStringExtra("ORP_EXT")
        val pHmExt = intent.getStringExtra("PHM_EXT")
        // Turbidimetro
        val turbExt = intent.getStringExtra("TURB_EXT")
        // pHmetro
        val pHExt = intent.getStringExtra("PH_EXT")
        // Erros
        var t3ErrosEquip = intent.getStringExtra("T3_ERROS_EQUIP")
        var t3ErrosPar = intent.getStringExtra("T3_ERROS_PAR")
        var t3ErrosVal = intent.getStringExtra("T3_ERROS_VAL")

        // 3a Atividade
        // Multiparametro
        val od1 = intent.getStringExtra("OD1")
        val od2 = intent.getStringExtra("OD2")
        val ec1 = intent.getStringExtra("EC1")
        val ec2 = intent.getStringExtra("EC2")
        val orp1 = intent.getStringExtra("ORP1")
        val orp2 = intent.getStringExtra("ORP2")
        val pHm1 = intent.getStringExtra("PHM1")
        val pHm2 = intent.getStringExtra("PHM2")
        val temp1 = intent.getStringExtra("TEMP1")
        val temp2 = intent.getStringExtra("TEMP2")
        // Turbidimetro
        val turb1 = intent.getStringExtra("TURB1")
        val turb2 = intent.getStringExtra("TURB2")
        // pHmetro
        val pH1 = intent.getStringExtra("PH1")
        val pH2 = intent.getStringExtra("PH2")
        // Erros
        var t4ErrosEquip = intent.getStringExtra("T4_ERROS_EQUIP")
        var t4ErrosPar = intent.getStringExtra("T4_ERROS_PAR")
        var t4ErrosVal1 = intent.getStringExtra("T4_ERROS_VAL1")
        var t4ErrosVal2 = intent.getStringExtra("T4_ERROS_VAL2")

        Log.i("\t-----", "----")
        Log.i("\tLIFE CYCLE 1st Activ", "onStart()")
        Log.i("\tResponsável: ", responsavel.toString())

        // Se algumas dessas variáveis vem pelo Intent como não nulas,
        // Elas devem ser atualizadas no layout
        if (responsavel != null) {var editResponsavel = findViewById<EditText>(R.id.responsavel);editResponsavel.setText(responsavel.toString())}
        if (ft14 != null) {var editFt14 = findViewById<EditText>(R.id.ft14);editFt14.setText(ft14.toString())}
        if (data != null) {var editData = findViewById<EditText>(R.id.data);editData.setText(data.toString())}
        if (multiparametro != null) {var editMultiparametro = findViewById<EditText>(R.id.multiparametro);editMultiparametro.setText(multiparametro.toString())}
        if (turbidimetro != null) {var editTurbidimetro = findViewById<EditText>(R.id.turbidimetro);editTurbidimetro.setText(turbidimetro.toString())}
        if (pHmetro != null) {var editPHmetro = findViewById<EditText>(R.id.pHmetro);editPHmetro.setText(pHmetro.toString())}

        // AVANÇAR >>>>>>>>>>
        var advance = findViewById<Button>(R.id.avancar)
        advance.setOnClickListener {
            var responsavel = findViewById<EditText>(R.id.responsavel)
            var ft14 = findViewById<EditText>(R.id.ft14)
            var data = findViewById<EditText>(R.id.data)
            var multiparametro = findViewById<EditText>(R.id.multiparametro)
            var turbidimetro = findViewById<EditText>(R.id.turbidimetro)
            var pHmetro = findViewById<EditText>(R.id.pHmetro)

            Log.i("\t\nresponsavel:", responsavel.text.toString() + ' ' + responsavel.text.isNullOrEmpty().toString())
            Log.i("\tft14:", ft14.text.toString() + ' ' + ft14.text.isNullOrEmpty().toString())
            Log.i("\tdata:", data.text.toString() + ' ' + data.text.isNullOrEmpty().toString())

            if (
                responsavel.text.toString().isNullOrEmpty() ||
                ft14.text.toString().isNullOrEmpty() ||
                data.text.toString().isNullOrEmpty() ||
                !(!multiparametro.text.toString().isNullOrEmpty() ||
                        !turbidimetro.text.toString().isNullOrEmpty() ||
                        !pHmetro.text.toString().isNullOrEmpty())
            ) { var builder = AlertDialog.Builder(this)
                builder.setMessage("Faltaram informações\n\nAlém das informações gerais,\npreencha ao menos um equipamento.")
                builder.setPositiveButton("OK", null)
                builder.setCancelable(false)
                // Ajustar o tamanho da Alertbox
                val dialog = builder.show()
                val textView = dialog.findViewById<View>(android.R.id.message) as TextView?
                textView!!.textSize = 14f


            } else {
                Log.i("\t-----", "----")
                Log.i("\t1 atividade", "AVANCOU!")

                var i = Intent(this, Tela3_ChecagemIntermediaria::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                i.putExtra("RESPONSAVEL", responsavel.text.toString())
                i.putExtra("FT14", ft14.text.toString())
                i.putExtra("DATA", data.text.toString())

                i.putExtra("MULTIPARAMETRO", multiparametro.text.toString())
                i.putExtra("TURBIDIMETRO", turbidimetro.text.toString())
                i.putExtra("PHMETRO", pHmetro.text.toString())

                Log.i("\tRESPONSAVEL:", i.getStringExtra("RESPONSAVEL") + "; " + responsavel.text.toString() + "; " + multiparametro.text.isNullOrEmpty().toString())
                Log.i("\tFT14:", i.getStringExtra("FT14") + "; " + ft14.text.toString() + "; " + turbidimetro.text.isNullOrEmpty().toString())
                Log.i("\tDATA:", i.getStringExtra("DATA") + "; " + data.text.toString() + "; " + pHmetro.text.isNullOrEmpty().toString())
                Log.i("\tmultiparametro:", i.getStringExtra("MULTIPARAMETRO") + "; " + multiparametro.text.toString() + "; " + multiparametro.text.isNullOrEmpty().toString())
                Log.i("\tturbidimetro:", i.getStringExtra("TURBIDIMETRO") + "; " + turbidimetro.text.toString() + "; " + turbidimetro.text.isNullOrEmpty().toString())
                Log.i("\tpHmetro:", i.getStringExtra("PHMETRO") + "; " + pHmetro.text.toString() + "; " + pHmetro.text.isNullOrEmpty().toString())

                i.putExtra("OD_EXT", odExt)
                i.putExtra("CE_EXT", ceExt)
                i.putExtra("ORP_EXT", orpExt)
                i.putExtra("PHM_EXT", pHmExt)
                i.putExtra("TURB_EXT", turbExt)
                i.putExtra("PH_EXT", pHExt)
                i.putExtra("T3_ERROS_EQUIP", t3ErrosEquip)
                i.putExtra("T3_ERROS_PAR", t3ErrosPar)
                i.putExtra("T3_ERROS_VAL", t3ErrosVal)

                i.putExtra("OD1", od1)
                i.putExtra("OD2", od2)
                i.putExtra("EC1", ec1)
                i.putExtra("EC2", ec2)
                i.putExtra("ORP1", orp1)
                i.putExtra("ORP2", orp2)
                i.putExtra("TEMP1", temp1)
                i.putExtra("TEMP2", temp2)
                i.putExtra("PHM1", pHm1)
                i.putExtra("PHM2", pHm2)
                i.putExtra("PH1", pH1)
                i.putExtra("PH2", pH2)
                i.putExtra("TURB1", turb1)
                i.putExtra("TURB2", turb2)
                i.putExtra("T4_ERROS_EQUIP", t4ErrosEquip)
                i.putExtra("T4_ERROS_PAR", t4ErrosPar)
                i.putExtra("T4_ERROS_VAL1", t4ErrosVal1)
                i.putExtra("T4_ERROS_VAL2", t4ErrosVal2)

                startActivity(i)
                finish()
            }
        }

        // frog.wav
        var frogCount = 0
        var frog = findViewById<ImageView>(R.id.frog)
        frog.setOnClickListener {
            frogCount += 1
            if (frogCount%10 == 0){
                var mediaPlayer = MediaPlayer.create(this, R.raw.frog)
                mediaPlayer.start()

                var builder = AlertDialog.Builder(this)
                builder.setMessage("*croac croac*")
                builder.setCancelable(false)
                val dialog = builder.show()
                val textView = dialog.findViewById<View>(android.R.id.message) as TextView?
                textView!!.textSize = 14f

                val timer = object: CountDownTimer(500, 500) {
                    override fun onTick(millisUntilFinished: Long) {}
                    override fun onFinish() {dialog.cancel()}
                }
                timer.start()
            }
        }

        // Aqui é preenchida a data automaticamente
        var textView: TextView = findViewById(R.id.data)
        textView.text = SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis())
        var cal = Calendar.getInstance()
        var dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                var myFormat = "dd/MM/yyyy"
                var sdf = SimpleDateFormat(myFormat, Locale.US)
                textView.text = sdf.format(cal.time)

            }

        textView.setOnClickListener {
            DatePickerDialog(
                this, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

    }

    // Back button closes application
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
