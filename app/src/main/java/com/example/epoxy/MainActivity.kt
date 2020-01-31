package com.example.epoxy

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.example.epoxy.epoxy.PhotoEpoxy
import com.example.epoxy.epoxy.User
import com.example.epoxy.epoxy.photoLayoutView
import com.example.epoxy.epoxy.userLayoutView
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthSettings
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.user_epoxy.*
import java.util.*


class MainActivity : AppCompatActivity() {


    private lateinit var firebaseDatabase : FirebaseDatabase
    private lateinit var authStateListener : FirebaseAuth.AuthStateListener
    private lateinit var firebaseAuth: FirebaseAuth

    private val RC_SIGN_IN = 1
    private val EMAIL : String = "email"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_epoxy)
        firebaseDatabase = FirebaseDatabase.getInstance()
        firebaseAuth = FirebaseAuth.getInstance()
        MultiDex.install(this)

           val providers = listOf<AuthUI.IdpConfig>(AuthUI.IdpConfig.GoogleBuilder().build(),
               AuthUI.IdpConfig.FacebookBuilder().build(), AuthUI.IdpConfig.EmailBuilder().build())

        authStateListener = FirebaseAuth.AuthStateListener {


                val user = firebaseAuth.currentUser
                if(user!=null){
                }
                else{
                    startActivityForResult(
                        AuthUI.getInstance().createSignInIntentBuilder()
                            .setIsSmartLockEnabled(false)
                            .setAvailableProviders(providers)
                            .build(), RC_SIGN_IN)
                }
            }



        epoxy_recycler_view.buildModelsWith {
            it.apply {
                User.getSampleUsers()
                    .forEachIndexed{
                        index, user -> userLayoutView {
                        id(index)
                        name(user.name)
                        age("${user.age}")
                        email(user.email)
                        itemClickListener{
                            _ -> Toast.makeText(this@MainActivity,
                            "You clicked on item ${user.name}", Toast.LENGTH_SHORT).show()
                        }
                        if(index.rem(2) == 0) background(Color.GREEN)
                    }
                    }
                PhotoEpoxy.getPhotoSamples().forEachIndexed{
                    index, photoEpoxy -> photoLayoutView {
                    id(index)
                    title(photoEpoxy.title)
                    photo(photoEpoxy.photoUrl)
                    itemClickListener{
                        _ -> Toast.makeText(this@MainActivity, "You clicked on" +
                            " item ${photoEpoxy.title}", Toast.LENGTH_SHORT).show()
                    }
                }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        firebaseAuth.addAuthStateListener (authStateListener)
    }

    override fun onPause() {
        super.onPause()
        firebaseAuth.removeAuthStateListener(authStateListener)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == RC_SIGN_IN){
            if(resultCode == Activity.RESULT_OK){
                Toast.makeText(this, "You just signed in!", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "Sign in canceled", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
