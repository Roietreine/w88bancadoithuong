package project.rr.w88bancadoithuong.common

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DbFirebase {

    lateinit var databaseReference: DatabaseReference
    fun getDatabase(): DatabaseReference {
        val database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference("jumpcode")
        return databaseReference
    }
}