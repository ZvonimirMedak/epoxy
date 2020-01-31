package com.example.epoxy.epoxy

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.bumptech.glide.Glide
import com.example.epoxy.R

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class PhotoLayoutView @JvmOverloads constructor(
    context: Context,
    attributeSet : AttributeSet? = null,
    defStyle : Int = 0
) : ConstraintLayout(context, attributeSet, defStyle){
    private val rootLayout: ConstraintLayout
    private val imageView : ImageView
    private val titleTextView : TextView

    init {
        View.inflate(context, R.layout.epoxy_photo_item, this)
        rootLayout = findViewById(R.id.photo_item)
        imageView = findViewById(R.id.photo_view)
        titleTextView = findViewById(R.id.photo_title)
    }

    @TextProp
    fun setTitle(title : CharSequence){
        titleTextView.text = title
    }
    @TextProp
    fun setPhoto(photoUrl : CharSequence){
        Glide.with(imageView.context).load(photoUrl).into(imageView)
    }
    @CallbackProp
    fun setItemClickListener(listener: OnClickListener?){
        rootLayout.setOnClickListener(listener)
    }
}