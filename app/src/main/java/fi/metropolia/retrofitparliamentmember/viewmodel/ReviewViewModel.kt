package fi.metropolia.retrofitparliamentmember.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import fi.metropolia.retrofitparliamentmember.repository.ReviewRepository

class ReviewViewModel(application: Application): AndroidViewModel(application) {
    val reviewRepo =  ReviewRepository(application)
}