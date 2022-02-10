/*
TELA DE BEM-VINDO === ATIVIDADE INICIAL
Precisão e Exatidão devem ser garantidas conforme nosso certificado ISO
precisão -> duplicata de ensaio em campo (água deionizada/mineral)
exatidão -> semelhante ao valor previsto (MR)

Isso é feito toda manhã antes do campo.
Esses dados eram enviados para Lisana/Joana que validavam no Excel

> Mostra o logo da SAPOTEC
> Mostra "bem-vindo"
> Vai para "Inseririnfos"

ATIVIDADE ANTERIOR: -
ATIVIDADE SUBSEQUENTE: InserirInfos
* */

package com.example.accuracy_precision

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.accuracy_precision.R


class Tela1_Abertura : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela1_abertura)

        val openingActivity = findViewById<ImageView>(R.id.i_abertura)
        val openingActivity2 = findViewById<TextView>(R.id.textView2)

        openingActivity.alpha = 0f
        openingActivity2.alpha = 0f

        openingActivity.animate().setDuration(1000).alpha(1f).withEndAction {
            openingActivity2.animate().setDuration(1000).alpha(1f).withEndAction{
                val i = Intent(this, Tela2_InformacoesGerais::class.java)
                startActivity(i)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
            }
        }
    }
}