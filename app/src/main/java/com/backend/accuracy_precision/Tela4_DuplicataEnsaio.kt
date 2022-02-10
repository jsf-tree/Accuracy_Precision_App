/*
CHECAR A PRECISÃO
Aqui é checada a exatidão

Ambos valores podem no máximo ter DPR até 20

DPR = |val1 - val2| / |(val1 + val2)/2| * 100

ATIVIDADE ANTERIOR: DuplicataPrecisao
ATIVIDADE SUBSEQUENTE: Compartilhar
* */

package com.backend.accuracy_precision

import android.annotation.SuppressLint
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
import kotlin.math.abs
import com.example.accuracy_precision.R

class Tela4_DuplicataEnsaio : AppCompatActivity() {
    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela4_duplicataensaio)

        fun dpr(n1: String, n2: String): Double {
            /*
            Calcula o desvio padrão relativo
            DPR = |val1 - val2| / |(val1 + val2)/2| * 100
            * */
            val val1 = n1.toDouble()
            val val2 = n2.toDouble()
            return abs(val1 - val2) / (abs(val1 + val2) / 2) * 100
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
        val t3ErrosEquip = intent.getStringExtra("T3_ERROS_EQUIP")
        val t3ErrosPar = intent.getStringExtra("T3_ERROS_PAR")
        val t3ErrosVal = intent.getStringExtra("T3_ERROS_VAL")

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
        var t4ErrosEquipString = intent.getStringExtra("T4_ERROS_EQUIP")
        var t4ErrosParString = intent.getStringExtra("T4_ERROS_PAR")
        var t4ErrosVal1String = intent.getStringExtra("T4_ERROS_VAL")
        var t4ErrosVal2String = intent.getStringExtra("T4_ERROS_VAL")

        // ESPAÇO PARA DEBUGGAR
        Log.i("\t-----", "----")
        Log.i("\tLIFE CYCLE 2st Activ", "onCreate()")

        // Restaurar estado das variáveis se não-nulas
        if (od1 != null) {val editText = findViewById<EditText>(R.id.od1); editText.setText(od1.toString())}
        if (od2 != null) {val editText = findViewById<EditText>(R.id.od2); editText.setText(od2.toString())}
        if (ec1 != null) {val editText = findViewById<EditText>(R.id.ec1); editText.setText(ec1.toString())}
        if (ec2 != null) {val editText = findViewById<EditText>(R.id.ec2); editText.setText(ec2.toString())}
        if (orp1 != null) {val editText = findViewById<EditText>(R.id.orp1); editText.setText(orp1.toString())}
        if (orp2 != null) {val editText = findViewById<EditText>(R.id.orp2); editText.setText(orp2.toString())}
        if (temp1 != null) {val editText = findViewById<EditText>(R.id.temp1); editText.setText(temp1.toString())}
        if (temp2 != null) {val editText = findViewById<EditText>(R.id.temp2); editText.setText(temp2.toString())}
        if (pHm1 != null) {val editText = findViewById<EditText>(R.id.pHm1); editText.setText(pHm1.toString())}
        if (pHm2 != null) {val editText = findViewById<EditText>(R.id.pHm2); editText.setText(pHm2.toString())}
        if (pH1 != null) {val editText = findViewById<EditText>(R.id.pH1); editText.setText(pH1.toString())}
        if (pH2 != null) {val editText = findViewById<EditText>(R.id.pH2); editText.setText(pH2.toString())}
        if (turb1 != null) {val editText = findViewById<EditText>(R.id.turb1); editText.setText(turb1.toString())}
        if (turb2 != null) {val editText = findViewById<EditText>(R.id.turb2); editText.setText(turb2.toString())}
        if (t4ErrosEquipString.isNullOrEmpty()) {t4ErrosEquipString = ""}
        if (t4ErrosParString.isNullOrEmpty()) {t4ErrosParString = ""}
        if (t4ErrosVal1String.isNullOrEmpty()) {t4ErrosVal1String = ""}
        if (t4ErrosVal2String.isNullOrEmpty()) {t4ErrosVal2String = ""}


        // Fazer o teste de validação
        var avancar: Boolean = false

        val validate = findViewById<Button>(R.id.validar)
        var t4ErrosEquip: MutableList<String> = ArrayList()
        var t4ErrosPar: MutableList<String> = ArrayList()
        var t4ErrosVal1: MutableList<String> = ArrayList()
        var t4ErrosVal2: MutableList<String> = ArrayList()

        validate.setOnClickListener {

            var builder = AlertDialog.Builder(this)
            builder.setMessage("Erros serão reportados ao laboratório.\n\nTem certeza que digitou corretamente?\n")
            builder.setNegativeButton("Não", null)
            builder.setPositiveButton("Sim") {dialog, which ->

                val od1 = findViewById<EditText>(R.id.od1).text.toString().replace(",",".")
                val od2 = findViewById<EditText>(R.id.od2).text.toString().replace(",",".")
                val ec1 = findViewById<EditText>(R.id.ec1).text.toString().replace(",",".")
                val ec2 = findViewById<EditText>(R.id.ec2).text.toString().replace(",",".")
                val orp1 = findViewById<EditText>(R.id.orp1).text.toString().replace(",",".")
                val orp2 = findViewById<EditText>(R.id.orp2).text.toString().replace(",",".")
                val pHm1 = findViewById<EditText>(R.id.pHm1).text.toString().replace(",",".")
                val pHm2 = findViewById<EditText>(R.id.pHm2).text.toString().replace(",",".")
                val temp1 = findViewById<EditText>(R.id.temp1).text.toString().replace(",",".")
                val temp2 = findViewById<EditText>(R.id.temp2).text.toString().replace(",",".")
                val turb1 = findViewById<EditText>(R.id.turb1).text.toString().replace(",",".")
                val turb2 = findViewById<EditText>(R.id.turb2).text.toString().replace(",",".")
                val pH1 = findViewById<EditText>(R.id.pH1).text.toString().replace(",",".")
                val pH2 = findViewById<EditText>(R.id.pH2).text.toString().replace(",",".")

                var odPrecResult = findViewById<TextView>(R.id.od_result)
                var ecPrecResult = findViewById<TextView>(R.id.ec_result)
                var orpPrecResult = findViewById<TextView>(R.id.orp_result)
                var tempPrecResult = findViewById<TextView>(R.id.temp_result)
                var pHmPrecResult = findViewById<TextView>(R.id.pHm_result)
                var turbPrecResult = findViewById<TextView>(R.id.turb_result)
                var phPrecResult = findViewById<TextView>(R.id.pH_result)

                var vazios = 0
                avancar = false

                var recalibrarMu: Boolean = false
                var recalibrarTurb: Boolean = false
                var recalibrarpH: Boolean = false

                // Caso haja multiparametro
                if (multiparametro.isNullOrEmpty()) {recalibrarMu = false; println("validouMu")} else
                {
                        if (od1.isNullOrEmpty() ||
                            od2.isNullOrEmpty() ||
                            ec1.isNullOrEmpty() ||
                            ec2.isNullOrEmpty() ||
                            orp1.isNullOrEmpty() ||
                            orp2.isNullOrEmpty() ||
                            pHm1.isNullOrEmpty() ||
                            pHm2.isNullOrEmpty() ||
                            temp1.isNullOrEmpty() ||
                            temp2.isNullOrEmpty()
                        )
                        {
                            var builder = AlertDialog.Builder(this)
                            vazios += 1
                            builder.setMessage("Dados não validados\n\nAo menos um campo do Multiparâmetro está vazio.")
                            builder.setPositiveButton("OK", null)
                            builder.setCancelable(false)
                            // Ajustar o tamanho da Alertbox
                            val dialog = builder.show()
                            val textView = dialog.findViewById<View>(android.R.id.message) as TextView?
                            textView!!.textSize = 14f
                        }
                        else {
                            try {
                                if (dpr(od1, od2) <= 20) {
                                    odPrecResult.text = getString(R.string.Aprovado)
                                    odPrecResult.setTextColor(Color.parseColor("#22B14C"))
                                } else {
                                    odPrecResult.text = getString(R.string.Reprovado)
                                    odPrecResult.setTextColor(Color.parseColor("#ED1C24"))
                                    recalibrarMu = recalibrarMu || true
                                    t4ErrosEquip.add("Multiparâmetro")
                                    t4ErrosPar.add("OD")
                                    t4ErrosVal1.add(od1.toString())
                                    t4ErrosVal2.add(od2.toString())
                                }
                                if (dpr(ec1, ec2) <= 20) {
                                    ecPrecResult.text = getString(R.string.Aprovado)
                                    ecPrecResult.setTextColor(Color.parseColor("#22B14C"))
                                } else {
                                    ecPrecResult.text = getString(R.string.Reprovado)
                                    ecPrecResult.setTextColor(Color.parseColor("#ED1C24"))
                                    recalibrarMu = recalibrarMu || true
                                    t4ErrosEquip.add("Multiparâmetro")
                                    t4ErrosPar.add("CE")
                                    t4ErrosVal1.add(ec1.toString())
                                    t4ErrosVal2.add(ec2.toString())
                                }
                                if (dpr(orp1, orp2) <= 20) {
                                    orpPrecResult.text = getString(R.string.Aprovado)
                                    orpPrecResult.setTextColor(Color.parseColor("#22B14C"))
                                } else {
                                    orpPrecResult.text = getString(R.string.Reprovado)
                                    orpPrecResult.setTextColor(Color.parseColor("#ED1C24"))
                                    recalibrarMu = recalibrarMu || true
                                    t4ErrosEquip.add("Multiparâmetro")
                                    t4ErrosPar.add("ORP")
                                    t4ErrosVal1.add(orp1.toString())
                                    t4ErrosVal2.add(orp2.toString())
                                }
                                if (dpr(temp1, temp2) <= 20) {
                                    tempPrecResult.text = getString(R.string.Aprovado)
                                    tempPrecResult.setTextColor(Color.parseColor("#22B14C"))
                                } else {
                                    tempPrecResult.text = getString(R.string.Reprovado)
                                    tempPrecResult.setTextColor(Color.parseColor("#ED1C24"))
                                    recalibrarMu = recalibrarMu || true
                                    t4ErrosEquip.add("Multiparâmetro")
                                    t4ErrosPar.add("Temp")
                                    t4ErrosVal1.add(temp1.toString())
                                    t4ErrosVal2.add(temp2.toString())
                                }
                                if (dpr(pHm1, pHm2) <= 20) {
                                    pHmPrecResult.text = getString(R.string.Aprovado)
                                    pHmPrecResult.setTextColor(Color.parseColor("#22B14C"))
                                } else {
                                    pHmPrecResult.text = getString(R.string.Reprovado)
                                    pHmPrecResult.setTextColor(Color.parseColor("#ED1C24"))
                                    recalibrarMu = recalibrarMu || true
                                    t4ErrosEquip.add("Multiparâmetro")
                                    t4ErrosPar.add("pH_Mu")
                                    t4ErrosVal1.add(pHm1.toString())
                                    t4ErrosVal2.add(pHm2.toString())
                                }

                            } catch (e: Exception) {
                                val builder = AlertDialog.Builder(this)
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
                // Se turbidímetro foi preenchido
                if (turbidimetro.isNullOrEmpty()) {recalibrarTurb = false; println("validouTurb")} else
                {
                    if (turb1.isNullOrEmpty() ||
                        turb2.isNullOrEmpty()
                    )
                    {
                        var builder = AlertDialog.Builder(this)
                        vazios += 1
                        builder.setMessage("Dados não validados\n\nAo menos um campo do Turbidímetro está vazio.")
                        builder.setPositiveButton("OK", null)
                        builder.setCancelable(false)
                        // Ajustar o tamanho da Alertbox
                        val dialog = builder.show()
                        val textView = dialog.findViewById<View>(android.R.id.message) as TextView?
                        textView!!.textSize = 14f
                    }
                    else {
                        try {
                            if (dpr(turb1, turb2) <= 20) {
                                turbPrecResult.text = getString(R.string.Aprovado)
                                turbPrecResult.setTextColor(Color.parseColor("#22B14C"))
                            } else {
                                turbPrecResult.text = getString(R.string.Reprovado)
                                turbPrecResult.setTextColor(Color.parseColor("#ED1C24"))
                                recalibrarTurb = recalibrarTurb || true
                                t4ErrosEquip.add("Turbidímetro")
                                t4ErrosPar.add("Turb")
                                t4ErrosVal1.add(turb1.toString())
                                t4ErrosVal2.add(turb2.toString())
                            }
                        } catch (e: Exception) {
                            val builder = AlertDialog.Builder(this)
                            vazios += 1
                            builder.setMessage("Não é possível validar\n\nPreencha dados do Turbidímetro!")
                            builder.setPositiveButton("OK", null)
                            builder.setCancelable(false)
                            // Ajustar o tamanho da Alertbox
                            val dialog = builder.show()
                            val textView = dialog.findViewById<View>(android.R.id.message) as TextView?
                            textView!!.textSize = 14f
                        }
                    }
                }
                // Se há pHmetro
                if (pHmetro.isNullOrEmpty()) {recalibrarpH = false; println("validoupH")} else
                {
                    if (pH1.isNullOrEmpty() ||
                        pH2.isNullOrEmpty()
                    )
                    {
                        var builder = AlertDialog.Builder(this)
                        vazios += 1
                        builder.setMessage("Dados não validados\n\nAo menos um campo do pHmetro está vazio.")
                        builder.setPositiveButton("OK", null)
                        builder.setCancelable(false)
                        // Ajustar o tamanho da Alertbox
                        val dialog = builder.show()
                        val textView = dialog.findViewById<View>(android.R.id.message) as TextView?
                        textView!!.textSize = 14f
                    }
                    else {
                        try {
                            if (dpr(pH1, pH2) <= 20) {
                                phPrecResult.text = getString(R.string.Aprovado)
                                phPrecResult.setTextColor(Color.parseColor("#22B14C"))
                            } else {
                                phPrecResult.text = getString(R.string.Reprovado)
                                phPrecResult.setTextColor(Color.parseColor("#ED1C24"))
                                recalibrarpH = recalibrarpH || true
                                t4ErrosEquip.add("pHmetro")
                                t4ErrosPar.add("pH")
                                t4ErrosVal1.add(pH1.toString())
                                t4ErrosVal2.add(pH2.toString())
                            }

                        } catch (e: Exception) {
                            val builder = AlertDialog.Builder(this)
                            vazios += 1
                            builder.setMessage("Não é possível validar\n\nPreencha dados do pHmetro!")
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

                if (recalibrarMu || recalibrarTurb || recalibrarpH) {
                    var builder = AlertDialog.Builder(this)
                    var mensagem = StringBuilder()
                    mensagem.append("Recalibrar seguintes parâmetros e checar novamente\n\n")

                    for (j in 1..t4ErrosEquip.size) {
                        mensagem.append("- " + t4ErrosEquip[j - 1] + " " + t4ErrosPar[j - 1] + " " + t4ErrosVal1[j - 1] + " " + t4ErrosVal2[j - 1] + "\n")
                    }
                    builder.setMessage(mensagem.toString().replace(".", ","))
                    builder.setPositiveButton("OK", null)
                    builder.setCancelable(false)
                    // Ajustar o tamanho da Alertbox
                    val dialog = builder.show()
                    val textView = dialog.findViewById<View>(android.R.id.message) as TextView?
                    textView!!.textSize = 14f

                    if (t4ErrosEquipString.isNullOrEmpty()) {
                        t4ErrosEquipString = t4ErrosEquip.toString().replace("[", "").replace("]", "").replace(" ", "")
                        t4ErrosParString = t4ErrosPar.toString().replace("[", "").replace("]", "").replace(" ", "")
                        t4ErrosVal1String = t4ErrosVal1.toString().replace("[", "").replace("]", "").replace(" ", "")
                        t4ErrosVal2String = t4ErrosVal2.toString().replace("[", "").replace("]", "").replace(" ", "")
                    } else {
                        t4ErrosEquipString += "," + t4ErrosEquip.toString().replace("[", "").replace("]", "").replace(" ", "")
                        t4ErrosParString += "," + t4ErrosPar.toString().replace("[", "").replace("]", "").replace(" ", "")
                        t4ErrosVal1String += "," + t4ErrosVal1.toString().replace("[", "").replace("]", "").replace(" ", "")
                        t4ErrosVal2String += "," + t4ErrosVal2.toString().replace("[", "").replace("]", "").replace(" ", "")
                    }

                    println("Lista de erros:")
                    println(">" + t4ErrosEquipString)
                    println(">" + t4ErrosParString)
                    println(">" + t4ErrosVal1String)
                    println(">" + t4ErrosVal2String)

                    t4ErrosEquip.clear()
                    t4ErrosPar.clear()
                    t4ErrosVal1.clear()
                    t4ErrosVal2.clear()

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
        val advance = findViewById<Button>(R.id.avancar)
        advance.setOnClickListener {
            if (avancar) {
                // Get the values
                val od1 = findViewById<EditText>(R.id.od1).text.toString()
                val od2 = findViewById<EditText>(R.id.od2).text.toString()
                val ec1 = findViewById<EditText>(R.id.ec1).text.toString()
                val ec2 = findViewById<EditText>(R.id.ec2).text.toString()
                val orp1 = findViewById<EditText>(R.id.orp1).text.toString()
                val orp2 = findViewById<EditText>(R.id.orp2).text.toString()
                val pHm1 = findViewById<EditText>(R.id.pHm1).text.toString()
                val pHm2 = findViewById<EditText>(R.id.pHm2).text.toString()
                val temp1 = findViewById<EditText>(R.id.temp1).text.toString()
                val temp2 = findViewById<EditText>(R.id.temp2).text.toString()

                val turb1 = findViewById<EditText>(R.id.turb1).text.toString()
                val turb2 = findViewById<EditText>(R.id.turb2).text.toString()

                val pH1 = findViewById<EditText>(R.id.pH1).text.toString()
                val pH2 = findViewById<EditText>(R.id.pH2).text.toString()

                val i = Intent(this, Tela5_Compartilhar::class.java)
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
                i.putExtra("T4_ERROS_EQUIP", t4ErrosEquipString)
                i.putExtra("T4_ERROS_PAR", t4ErrosParString)
                i.putExtra("T4_ERROS_VAL1", t4ErrosVal1String)
                i.putExtra("T4_ERROS_VAL2", t4ErrosVal2String)

                println("Intent passado")
                println(t4ErrosEquipString)
                println(t4ErrosParString)
                println(t4ErrosVal1String)
                println(t4ErrosVal2String)


                startActivity(i)
                finish()
            } else {
                val builder = AlertDialog.Builder(this)
                var mensagem = StringBuilder()
                mensagem.append("Impossível avançar\n\nMedições não validadas:\n")
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
        val retreat = findViewById<Button>(R.id.voltar)
        retreat.setOnClickListener {
            // Multiparametro
            val od1 = findViewById<EditText>(R.id.od1).text.toString()
            val od2 = findViewById<EditText>(R.id.od2).text.toString()
            val ec1 = findViewById<EditText>(R.id.ec1).text.toString()
            val ec2 = findViewById<EditText>(R.id.ec2).text.toString()
            val orp1 = findViewById<EditText>(R.id.orp1).text.toString()
            val orp2 = findViewById<EditText>(R.id.orp2).text.toString()
            val pHm1 = findViewById<EditText>(R.id.pHm1).text.toString()
            val pHm2 = findViewById<EditText>(R.id.pHm2).text.toString()
            val temp1 = findViewById<EditText>(R.id.temp1).text.toString()
            val temp2 = findViewById<EditText>(R.id.temp2).text.toString()
            // Turbidimetro
            val turb1 = findViewById<EditText>(R.id.turb1).text.toString()
            val turb2 = findViewById<EditText>(R.id.turb2).text.toString()
            // pHmetro
            val pH1 = findViewById<EditText>(R.id.pH1).text.toString()
            val pH2 = findViewById<EditText>(R.id.pH2).text.toString()


            val i = Intent(this, Tela3_ChecagemIntermediaria::class.java)
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
            i.putExtra("T4_ERROS_EQUIP", t4ErrosEquipString)
            i.putExtra("T4_ERROS_PAR", t4ErrosParString)
            i.putExtra("T4_ERROS_VAL1", t4ErrosVal1String)
            i.putExtra("T4_ERROS_VAL2", t4ErrosVal2String)

            startActivity(i)
            finish()
        }
    }
    override fun onBackPressed() {
        //super.onBackPressed();
        //Not calling **super**, disables back button in current screen.
    }
}