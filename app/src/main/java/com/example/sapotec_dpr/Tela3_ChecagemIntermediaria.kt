/*
CHECAR A EXATIDÃO
Aqui é checada a exatidão

Faixa de validação:
OD   dentro de [0,0.3]
CE   dentro de [1337.6 e 1478.4]
ORP  dentro de [209 e 249]
pH   dentro de [6.8 e 7.2]
Turb dentro de [8 e 12]

Padrões são:
OD   0.01
CE   1408
ORP  229
pH   7
Turb 10

ATIVIDADE ANTERIOR: InserirInfos
ATIVIDADE SUBSEQUENTE: DuplicataPrecisao
* */


package com.example.sapotec_dpr

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog


class Tela3_ChecagemIntermediaria : AppCompatActivity() {
    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela3_checagemintermediaria)

        Log.i("\tLIFE CYCLE 2nd Activ", "onCreate()")
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
        var t3ErrosEquipString = intent.getStringExtra("T3_ERROS_EQUIP")
        var t3ErrosParString = intent.getStringExtra("T3_ERROS_PAR")
        var t3ErrosValString = intent.getStringExtra("T3_ERROS_VAL")

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
        Log.i("\tLIFE CYCLE 2st Activ", "onStart()")
        Log.i("\tRESPONSAVEL:", responsavel + "; " + responsavel.isNullOrEmpty().toString())
        Log.i("\tFT14:", ft14 + "; " + ft14.isNullOrEmpty().toString())
        Log.i("\tDATA:", data + "; " + data.isNullOrEmpty().toString())
        Log.i("\tmultiparametro:", multiparametro + "; " + multiparametro.isNullOrEmpty().toString())
        Log.i("\tturbidimetro:", turbidimetro + "; " + turbidimetro.isNullOrEmpty().toString())
        Log.i("\tturbExt:", turbExt + "; " + turbExt.isNullOrEmpty().toString())
        Log.i("\tpHmetro:", pHmetro + "; " + pHmetro.isNullOrEmpty().toString())

        // Se algumas dessas variáveis vem pelo Intent como não nulas,
        // Elas devem ser atualizadas no layout
        if (odExt != null) {var editText = findViewById<EditText>(R.id.od_ext);editText.setText(odExt.toString())}
        if (ceExt != null) {var editText = findViewById<EditText>(R.id.ce_ext);editText.setText(ceExt.toString())}
        if (orpExt != null) {var editText = findViewById<EditText>(R.id.orp_ext);editText.setText(orpExt.toString())}
        if (pHmExt != null) {var editText = findViewById<EditText>(R.id.pHm_ext);editText.setText(pHmExt.toString())}
        if (turbExt != null) {var editText = findViewById<EditText>(R.id.turb_ext);editText.setText(turbExt.toString())}
        if (pHExt != null) {var editText = findViewById<EditText>(R.id.pH_ext);editText.setText(pHExt.toString())}
        if (t3ErrosEquipString.isNullOrEmpty()) {t3ErrosEquipString = ""}
        if (t3ErrosParString.isNullOrEmpty()) {t3ErrosParString = ""}
        if (t3ErrosValString.isNullOrEmpty()) {t3ErrosValString = ""}


        // Fazer o teste de Validação
        var avancar: Boolean = false

        var validate = findViewById<Button>(R.id.validar)
        var t3ErrosEquip: MutableList<String> = ArrayList()
        var t3ErrosPar: MutableList<String> = ArrayList()
        var t3ErrosVal: MutableList<String> = ArrayList()

        validate.setOnClickListener {

            var builder = AlertDialog.Builder(this)
            builder.setMessage("Erros serão reportados ao laboratório.\n\nTem certeza que digitou corretamente?\n")
            builder.setNegativeButton("Não", null)
            builder.setPositiveButton("Sim") {dialog, which ->
                var odExt = findViewById<EditText>(R.id.od_ext).text.toString().replace(",", ".")
                var ceExt = findViewById<EditText>(R.id.ce_ext).text.toString().replace(",", ".")
                var orpExt = findViewById<EditText>(R.id.orp_ext).text.toString().replace(",", ".")
                var pHmExt = findViewById<EditText>(R.id.pHm_ext).text.toString().replace(",", ".")
                var turbExt = findViewById<EditText>(R.id.turb_ext).text.toString().replace(",", ".")
                var pHExt = findViewById<EditText>(R.id.pH_ext).text.toString().replace(",", ".")
                var odExtResult = findViewById<TextView>(R.id.od_ext_result)
                var ceExtResult = findViewById<TextView>(R.id.ce_ext_result)
                var orpExtResult = findViewById<TextView>(R.id.orp_ext_result)
                var pHmExtResult = findViewById<TextView>(R.id.pHm_ext_result)
                var turbExtResult = findViewById<TextView>(R.id.turb_ext_result)
                var pHExtResult = findViewById<TextView>(R.id.pH_ext_result)

                var vazios = 0
                avancar = false

                var recalibrarMu: Boolean = false
                var recalibrarTurb: Boolean = false
                var recalibrarpH: Boolean = false

                // Caso haja multiparametro
                if (multiparametro.isNullOrEmpty()) {recalibrarMu = false; println("validouMu")} else
                {
                    if (odExt.isNullOrEmpty() ||
                        ceExt.isNullOrEmpty() ||
                        orpExt.isNullOrEmpty() ||
                        pHmExt.isNullOrEmpty()
                    )
                    {
                        var builder = AlertDialog.Builder(this)
                        vazios += 1
                        builder.setMessage("Dados não validados\nAo menos um campo do Multiparâmetro está vazio.")
                        builder.setPositiveButton("OK", null)
                        builder.setCancelable(false)
                        // Ajustar o tamanho da Alertbox
                        val dialog = builder.show()
                        val textView = dialog.findViewById<View>(android.R.id.message) as TextView?
                        textView!!.textSize = 14f
                    }
                    else {
                        try {
                            if (odExt.toDouble() in 0.0..0.3)
                            {                // Valor padrão: 0.01
                                odExtResult.text = getString(R.string.Aprovado)
                                odExtResult.setTextColor(Color.parseColor("#22B14C"))
                            } else {
                                odExtResult.text = getString(R.string.Reprovado)
                                odExtResult.setTextColor(Color.parseColor("#ED1C24"))
                                recalibrarMu = recalibrarMu || true
                                t3ErrosEquip.add("Multiparâmetro")
                                t3ErrosPar.add("OD")
                                t3ErrosVal.add(odExt.toString())
                            }

                            if (ceExt.toDouble() in 1337.6..1478.4)
                            {          // Valor padrão:  1408
                                ceExtResult.text = getString(R.string.Aprovado)
                                ceExtResult.setTextColor(Color.parseColor("#22B14C"))
                            } else {
                                ceExtResult.text = getString(R.string.Reprovado)
                                ceExtResult.setTextColor(Color.parseColor("#ED1C24"))
                                recalibrarMu = recalibrarMu || true
                                t3ErrosEquip.add("Multiparâmetro")
                                t3ErrosPar.add("CE")
                                t3ErrosVal.add(ceExt.toString())
                            }

                            if (orpExt.toDouble() in 209.0..249.0)
                            {           // Valor padrão: 229
                                orpExtResult.text = getString(R.string.Aprovado)
                                orpExtResult.setTextColor(Color.parseColor("#22B14C"))
                            } else {
                                orpExtResult.text = getString(R.string.Reprovado)
                                orpExtResult.setTextColor(Color.parseColor("#ED1C24"))
                                recalibrarMu = recalibrarMu || true
                                t3ErrosEquip.add("Multiparâmetro")
                                t3ErrosPar.add("ORP")
                                t3ErrosVal.add(orpExt.toString())
                            }

                            if (pHmExt.toDouble() in 6.8..7.2)
                            {             // Valor padrão: 7.00
                                pHmExtResult.text = getString(R.string.Aprovado)
                                pHmExtResult.setTextColor(Color.parseColor("#22B14C"))
                            } else {
                                pHmExtResult.text = getString(R.string.Reprovado)
                                pHmExtResult.setTextColor(Color.parseColor("#ED1C24"))
                                recalibrarMu = recalibrarMu || true
                                t3ErrosEquip.add("Multiparâmetro")
                                t3ErrosPar.add("pH_Mu")
                                t3ErrosVal.add(pHmExt.toString())
                            }
                        } catch (e: Exception) {
                            var builder = AlertDialog.Builder(this)
                            vazios += 1
                            builder.setMessage("Dados não validados\n\nPreencher no formato:\n7,1 (separado por vírgula)")
                            builder.setPositiveButton("OK", null)
                            builder.setCancelable(false)
                            // Ajustar o tamanho da Alertbox
                            val dialog = builder.show()
                            val textView = dialog.findViewById<View>(android.R.id.message) as TextView?
                            textView!!.textSize = 14f

                        }
                    }
                }
                // Caso haja turbidímetro
                if (turbidimetro.isNullOrEmpty()) {recalibrarTurb = false; println("validouTurb")} else
                {
                    if (turbExt.isNullOrEmpty())
                    {
                        var builder = AlertDialog.Builder(this)
                        vazios += 1
                        builder.setMessage("Dados não validados\n\nCampo do Turbidímetro vazio.")
                        builder.setPositiveButton("OK", null)
                        builder.setCancelable(false)
                        // Ajustar o tamanho da Alertbox
                        val dialog = builder.show()
                        val textView = dialog.findViewById<View>(android.R.id.message) as TextView?
                        textView!!.textSize = 14f
                    }
                    else{
                        try {
                            if (turbExt.toDouble() in 8.0..12.0) {             // Valor padrão: 10.00
                                turbExtResult.text = getString(R.string.Aprovado)
                                turbExtResult.setTextColor(Color.parseColor("#22B14C"))
                            } else {
                                turbExtResult.text = getString(R.string.Reprovado)
                                turbExtResult.setTextColor(Color.parseColor("#ED1C24"))
                                recalibrarTurb = recalibrarTurb || true
                                t3ErrosEquip.add("Turbidímetro")
                                t3ErrosPar.add("Turb")
                                t3ErrosVal.add(turbExt.toString())
                            }
                        } catch (e: Exception) {
                            var builder = AlertDialog.Builder(this)
                            vazios += 1
                            builder.setMessage("Dados não validados\n\nPreencher no formato:\n7,1 (separado por vírgula)")
                            builder.setPositiveButton("OK", null)
                            builder.setCancelable(false)
                            // Ajustar o tamanho da Alertbox
                            val dialog = builder.show()
                            val textView = dialog.findViewById<View>(android.R.id.message) as TextView?
                            textView!!.textSize = 14f
                        }
                    }
                }
                // Caso haja pHmetro
                if (pHmetro.isNullOrEmpty()) {recalibrarpH = false; println("validoupH")} else
                {
                    if (pHExt.isNullOrEmpty())
                    {
                        var builder = AlertDialog.Builder(this)
                        vazios += 1
                        builder.setMessage("Dados não validados\n\nCampo do pHmetro vazio.")
                        builder.setPositiveButton("OK", null)
                        builder.setCancelable(false)
                        // Ajustar o tamanho da Alertbox
                        val dialog = builder.show()
                        val textView = dialog.findViewById<View>(android.R.id.message) as TextView?
                        textView!!.textSize = 14f
                    }
                    else {
                        try {
                            println(pHExt.toString())
                            if (pHExt.toDouble() in 6.8..7.2) {             // Valor padrão: 7.00
                                pHExtResult.text = getString(R.string.Aprovado)
                                pHExtResult.setTextColor(Color.parseColor("#22B14C"))
                            } else {
                                pHExtResult.text = getString(R.string.Reprovado)
                                pHExtResult.setTextColor(Color.parseColor("#ED1C24"))
                                recalibrarpH = recalibrarpH || true
                                t3ErrosEquip.add("pHmetro")
                                t3ErrosPar.add("pH")
                                t3ErrosVal.add(pHExt.toString())
                            }
                        } catch (e: Exception) {
                            var builder = AlertDialog.Builder(this)
                            vazios += 1
                            builder.setMessage("Dados não validados\n\nPreencher no formato:\n7,1 (separado por vírgula)")
                            builder.setPositiveButton("OK", null)
                            builder.setCancelable(false)
                            // Ajustar o tamanho da Alertbox
                            val dialog = builder.show()
                            val textView = dialog.findViewById<View>(android.R.id.message) as TextView?
                            textView!!.textSize = 14f
                        }
                    }
                }

                if (vazios == 0) {avancar = true} else {avancar = false}

                println("Recalibrar:\n\n")
                println(recalibrarMu)
                println(recalibrarTurb)
                println(recalibrarpH)

                if (recalibrarMu || recalibrarTurb || recalibrarpH)
                {
                    var builder = AlertDialog.Builder(this)
                    var mensagem = StringBuilder()
                    mensagem.append("Recalibrar seguintes parâmetros e checar novamente\n\n")
                    for (j in 1..t3ErrosEquip.size) {
                        mensagem.append("- "+ t3ErrosEquip[j-1] + " " + t3ErrosPar[j-1] + " " + t3ErrosVal[j-1] + "\n")
                    }
                    builder.setMessage(mensagem.toString().replace(".",","))
                    builder.setPositiveButton("OK", null)
                    builder.setCancelable(false)
                    // Ajustar o tamanho da Alertbox
                    val dialog = builder.show()
                    val textView = dialog.findViewById<View>(android.R.id.message) as TextView?
                    textView!!.textSize = 14f

                    if (t3ErrosEquipString.isNullOrEmpty()){
                        t3ErrosEquipString = t3ErrosEquip.toString().replace("[","").replace("]","").replace(" ","")
                        t3ErrosParString = t3ErrosPar.toString().replace("[","").replace("]","").replace(" ","")
                        t3ErrosValString = t3ErrosVal.toString().replace("[","").replace("]","").replace(" ","")
                    }
                    else{
                        t3ErrosEquipString += "," + t3ErrosEquip.toString().replace("[","").replace("]","").replace(" ","")
                        t3ErrosParString += "," + t3ErrosPar.toString().replace("[","").replace("]","").replace(" ","")
                        t3ErrosValString += "," + t3ErrosVal.toString().replace("[","").replace("]","").replace(" ","")
                    }
                    println("teste")
                    println(">"+t3ErrosEquipString)
                    println(">"+t3ErrosParString)
                    println(">"+t3ErrosValString)

                    t3ErrosEquip.clear()
                    t3ErrosPar.clear()
                    t3ErrosVal.clear()

                    avancar = false
                }
            }
            builder.setCancelable(false)
            // Ajustar o tamanho da Alertbox
            val dialog = builder.show()
            val textView = dialog.findViewById<View>(android.R.id.message) as TextView?
            textView!!.textSize = 14f

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

        // AVANÇAR >>>>>>>>>>
        var advance = findViewById<Button>(R.id.avancar)
        advance.setOnClickListener {
            var odExt = findViewById<EditText>(R.id.od_ext).text.toString()
            var ceExt = findViewById<EditText>(R.id.ce_ext).text.toString()
            var orpExt = findViewById<EditText>(R.id.orp_ext).text.toString()
            var pHmExt = findViewById<EditText>(R.id.pHm_ext).text.toString()
            var turbExt = findViewById<EditText>(R.id.turb_ext).text.toString()
            var pHExt = findViewById<EditText>(R.id.pH_ext).text.toString()

            if (avancar) {
                Log.i("\t2 atividade", "AVANCOU!")
                var i = Intent(this, Tela4_DuplicataEnsaio::class.java)
                i.putExtra("RESPONSAVEL", responsavel)
                i.putExtra("FT14", ft14)
                i.putExtra("DATA", data)
                i.putExtra("MULTIPARAMETRO", multiparametro)
                i.putExtra("TURBIDIMETRO", turbidimetro)
                i.putExtra("PHMETRO", pHmetro)

                i.putExtra("OD_EXT", odExt)
                i.putExtra("CE_EXT", ceExt)
                i.putExtra("ORP_EXT", orpExt)
                i.putExtra("PHM_EXT", pHmExt)
                i.putExtra("TURB_EXT", turbExt)
                i.putExtra("PH_EXT", pHExt)
                i.putExtra("T3_ERROS_EQUIP", t3ErrosEquipString)
                i.putExtra("T3_ERROS_PAR", t3ErrosParString)
                i.putExtra("T3_ERROS_VAL", t3ErrosValString)

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
            } else {
                var builder = AlertDialog.Builder(this)
                var mensagem = StringBuilder()
                mensagem.append("Impossível avançar!\n\n")
                mensagem.append("Validar medições do\n")
                if (!multiparametro.isNullOrEmpty())
                {mensagem.append("- Multiparametro\n")}
                if (!turbidimetro.isNullOrEmpty())
                {mensagem.append("- Turbidímetro\n")}
                if (!pHmetro.isNullOrEmpty())
                {mensagem.append("- pHmetro\n")}
                builder.setMessage(mensagem.toString())
                builder.setPositiveButton("OK", null)
                builder.setCancelable(false)
                // Ajustar o tamanho da Alertbox
                val dialog = builder.show()
                val textView = dialog.findViewById<View>(android.R.id.message) as TextView?
                textView!!.textSize = 14f

            }
        }

        // VOLTAR <<<<<<<<<<<<
        var retreat = findViewById<Button>(R.id.voltar)
        retreat.setOnClickListener {
            var odExt = findViewById<EditText>(R.id.od_ext).text.toString()
            var ceExt = findViewById<EditText>(R.id.ce_ext).text.toString()
            var orpExt = findViewById<EditText>(R.id.orp_ext).text.toString()
            var pHmExt = findViewById<EditText>(R.id.pHm_ext).text.toString()
            var turbExt = findViewById<EditText>(R.id.turb_ext).text.toString()
            var pHExt = findViewById<EditText>(R.id.pH_ext).text.toString()

            var i = Intent(this, Tela2_InformacoesGerais::class.java)

            Log.i("\t2 atividade", "VOLTOU!")
            i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            i.putExtra("RESPONSAVEL", responsavel)
            i.putExtra("FT14", ft14)
            i.putExtra("DATA", data)
            i.putExtra("MULTIPARAMETRO", multiparametro)
            i.putExtra("TURBIDIMETRO", turbidimetro)
            i.putExtra("PHMETRO", pHmetro)

            i.putExtra("OD_EXT", odExt)
            i.putExtra("CE_EXT", ceExt)
            i.putExtra("ORP_EXT", orpExt)
            i.putExtra("PHM_EXT", pHmExt)
            i.putExtra("TURB_EXT", turbExt)
            i.putExtra("PH_EXT", pHExt)
            i.putExtra("T3_ERROS_EQUIP", t3ErrosEquipString)
            i.putExtra("T3_ERROS_PAR", t3ErrosParString)
            i.putExtra("T3_ERROS_VAL", t3ErrosValString)

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

    override fun onBackPressed() {
        //super.onBackPressed();
        //Not calling **super**, disables back button in current screen.
    }
}