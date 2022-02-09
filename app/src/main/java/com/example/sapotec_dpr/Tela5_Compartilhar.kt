/*
COMPARTILHAMENTO DOS DADOS COM NSR === ENVIAR E-MAIL
Aqui são enviados os dados preenchidos para NSR
no formato:


ATIVIDADE ANTERIOR: DuplicataPrecisao
ATIVIDADE SUBSEQUENTE: -
* */

package com.example.sapotec_dpr

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*


class Tela5_Compartilhar : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela5_compartilhar)

        fun haErro(equip: String, par: String, listaEquip: String, listaPar: String, listaValor1: String, listaValor2: String = "vazio"): String {
            /*
            Há três listas em que são mantidos os erros: equipamento, parâmetro, valores (pode haver um 4a lista se for Duplicata)
            Nessas listas, buscam-se os parâmetros que vão ser impressos.
            Caso algum seja encontrado, será impresso com a tag "Reprovado"
            * */

            var texto = ""

            if (equip in listaEquip.split(",") && par in listaPar.split(","))
            {
                for (j in 1..listaEquip.split(",").size)
                {
                    if (listaPar.split(",")[j-1] == par && listaEquip.split(",")[j-1] == equip){
                        texto += par + ": " + listaValor1.split(",")[j-1]
                        if (listaValor2 != "vazio")
                        {
                            texto += "; "  + listaValor2.split(",")[j-1]
                        }
                        texto += " (X - Reprovado)\n"
                    }
                }
            }
            return texto
        }


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
        var t3ErrosEquip = intent.getStringExtra("T3_ERROS_EQUIP").toString()
        var t3ErrosPar = intent.getStringExtra("T3_ERROS_PAR").toString()
        var t3ErrosVal = intent.getStringExtra("T3_ERROS_VAL").toString()

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
        var t4ErrosEquip = intent.getStringExtra("T4_ERROS_EQUIP").toString()
        var t4ErrosPar = intent.getStringExtra("T4_ERROS_PAR").toString()
        var t4ErrosVal1 = intent.getStringExtra("T4_ERROS_VAL1").toString()
        var t4ErrosVal2 = intent.getStringExtra("T4_ERROS_VAL2").toString()

        // Debugging
        Log.i("\t-----", "----")
        Log.i("\tLIFE CYCLE 4st Activ", "onStart()")
        println("t3ErrosEquip: "+t3ErrosEquip)
        println("t3ErrosPar: "+t3ErrosPar)
        println("t3ErrosVal2: "+t3ErrosVal)
        println("t4ErrosEquip: "+t4ErrosEquip)
        println("t4ErrosPar: "+t4ErrosPar)
        println("t4ErrosVal1: "+t4ErrosVal1)
        println("t4ErrosVal2: "+t4ErrosVal2)


        val horario: String = SimpleDateFormat("HH:mm:ss").format(System.currentTimeMillis()).toString()

        // ENVIAR E-MAIL:
        val enviar = findViewById<Button>(R.id.email)
        enviar.setOnClickListener{
            val recipientList = "lmoraes@sapotec.com.br, joana@sapotec.com.br"
            val recipients: Array<String> = recipientList.split(",").toTypedArray()

            val subject = "DADOS FT34 - [$responsavel;$data]"
            var text = "Prezada responsável do Laboratório,\n\n" +
                    "Abaixo seguem os dados da checagem de exatidão e precisão do ensaio\n\n" +
                    "Data: $data\n" +
                    "Horário: $horario\n\n"

            var texto_erro = ""


            text += "--- INFORMAÇÕES GERAIS ---\n" +
                    "Responsável: $responsavel\n" +
                    "FT14: $ft14\n" +
                    "Equipamentos utilizados:\n"
            if (!multiparametro.isNullOrEmpty()) {
                text += "- Multiparâmetro: $multiparametro\n"
            }
            if (!turbidimetro.isNullOrEmpty()) {
                text += "- Turbidímetro: $turbidimetro\n"
            }
            if (!pHmetro.isNullOrEmpty()) {
                text += "- pHmetro: $pHmetro\n"
            }
            text += "\n"

            text += "--- CHECAGEM INTERMEDIÁRIA ---\n"
            if (!multiparametro.isNullOrEmpty()) {
                text += haErro("Multiparâmetro", "OD", t3ErrosEquip, t3ErrosPar, t3ErrosVal)
                text += "OD: $odExt (✓ - Aprovado)\n"
                text += haErro("Multiparâmetro", "CE", t3ErrosEquip, t3ErrosPar, t3ErrosVal)
                text += "CE: $ceExt (✓ - Aprovado)\n"
                text += haErro("Multiparâmetro", "ORP", t3ErrosEquip, t3ErrosPar, t3ErrosVal)
                text += "ORP: $orpExt (✓ - Aprovado)\n"
                text += haErro("Multiparâmetro", "pH_Mu", t3ErrosEquip, t3ErrosPar, t3ErrosVal)
                text += "pH_Mu: $pHmExt (✓ - Aprovado)\n"
            }
            if (!turbidimetro.isNullOrEmpty()) {
                text += haErro("Turbidímetro", "Turb", t3ErrosEquip, t3ErrosPar, t3ErrosVal)
                text += "Turb: $turbExt (✓ - Aprovado)\n"

            }
            if (!pHmetro.isNullOrEmpty()) {
                text += haErro("pHmetro", "pH", t3ErrosEquip, t3ErrosPar, t3ErrosVal)
                text += "pH: $pHExt (✓ - Aprovado)\n"
            }
            text += "\n"

            text += "--- DUPLICATA DE ENSAIO ---\n"
            if (!multiparametro.isNullOrEmpty()) {
                text += haErro("Multiparâmetro", "OD", t4ErrosEquip, t4ErrosPar, t4ErrosVal1, t4ErrosVal2)
                text += "OD: $od1; $od2 (✓ - Aprovado)\n"
                text += haErro("Multiparâmetro", "CE", t4ErrosEquip, t4ErrosPar, t4ErrosVal1, t4ErrosVal2)
                text += "CE: $ec1; $ec2 (✓ - Aprovado)\n"
                text += haErro("Multiparâmetro", "ORP", t4ErrosEquip, t4ErrosPar, t4ErrosVal1, t4ErrosVal2)
                text += "ORP: $orp1; $orp2 (✓ - Aprovado)\n"
                text += haErro("Multiparâmetro", "Temp", t4ErrosEquip, t4ErrosPar, t4ErrosVal1, t4ErrosVal2)
                text += "Temp: $temp1; $temp2 (✓ - Aprovado)\n"
                text += haErro("Multiparâmetro", "pH_Mu", t4ErrosEquip, t4ErrosPar, t4ErrosVal1, t4ErrosVal2)
                text += "pH_Mu: $pHm1; $pHm2 (✓ - Aprovado)\n"
            }
            if (!turbidimetro.isNullOrEmpty()) {
                text += haErro("Turbidímetro", "Turb", t4ErrosEquip, t4ErrosPar, t4ErrosVal1, t4ErrosVal2)
                text += "turb: $turb1; $turb2 (✓ - Aprovado)\n"
            }
            if (!pHmetro.isNullOrEmpty()) {
                text += haErro("pHmetro", "pH", t4ErrosEquip, t4ErrosPar, t4ErrosVal1, t4ErrosVal2)
                text += "pH: $pH1; $pH2 (✓ - Aprovado)\n"
            }
            text += "\n"

            text += "\nCordialmente,\n" +
                    "$responsavel"

            text = text.replace(".",",").replace("pH_Mu","pH Mu")

            val i = Intent(Intent.ACTION_SEND)
            i.putExtra(Intent.EXTRA_EMAIL, recipients)
            i.putExtra(Intent.EXTRA_SUBJECT, subject)
            i.putExtra(Intent.EXTRA_TEXT, text)

            i.setType("message/rfc822")
            startActivity(Intent.createChooser(i, "Choose an email client"))
        }

        // VOLTAR <<<<<<<<<<<<
        val retreat = findViewById<Button>(R.id.voltar)
        retreat.setOnClickListener {
            val i = Intent(this, Tela4_DuplicataEnsaio::class.java)
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


        fun positiveButtonClick(): String {
            /*
            Há três listas em que são mantidos os erros: equipamento, parâmetro, valores (pode haver um 4a lista se for Duplicata)
            Nessas listas, buscam-se os parâmetros que vão ser impressos.
            Caso algum seja encontrado, será impresso com a tag "Reprovado"
            * */
            finishAndRemoveTask()
            return "a"
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


        // VOLTAR <<<<<<<<<<<<
        val fechar = findViewById<Button>(R.id.fechar)
        fechar.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Tem certeza que deseja finalizar?")
            builder.setNegativeButton("Não", null)
            builder.setPositiveButton("Sim", DialogInterface.OnClickListener { dialog, id ->
                // FIRE ZE MISSILES!
                finishAndRemoveTask()
            })
            builder.setCancelable(false)
            // Ajustar o tamanho da Alertbox
            val dialog = builder.show()
            val textView = dialog.findViewById<View>(android.R.id.message) as TextView?
            textView!!.textSize = 14f
        }
    }
    override fun onBackPressed() {
        //super.onBackPressed();
        //Not calling **super**, disables back button in current screen.
    }
}


