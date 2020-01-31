package com.example.epoxy.epoxy

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.example.epoxy.R

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class UserLayoutView @JvmOverloads constructor(
    context: Context,
    atributeSet : AttributeSet? = null,
    defStyle : Int = 0
    ): ConstraintLayout(context, atributeSet, defStyle){
    private val rootLayout : ConstraintLayout
    private val nameTextView : TextView
    private val emailTextView : TextView
    private val ageTextView : TextView

    init {
        View.inflate(context, R.layout.epoxy_item, this)
        rootLayout = findViewById(R.id.item_layout)
        nameTextView = findViewById(R.id.name_text_view)
        emailTextView = findViewById(R.id.emailTextView)
        ageTextView = findViewById(R.id.ageTextView)
    }
    @TextProp
    fun setName(name: CharSequence){
        nameTextView.text = name
    }

    @TextProp
    fun setEmail(email : CharSequence){
        emailTextView.text = email
    }

    @TextProp
    fun setAge(age : CharSequence){
        ageTextView.text = age
    }
    @ModelProp
    fun setBackground(background: Int){
        rootLayout.setBackgroundColor(background)
    }

    @CallbackProp
    fun setItemClickListener(listener: OnClickListener?){
        rootLayout.setOnClickListener(listener)
    }

}