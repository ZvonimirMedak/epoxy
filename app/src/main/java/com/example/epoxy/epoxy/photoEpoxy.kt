package com.example.epoxy.epoxy

data class PhotoEpoxy(
    val title : String,
    val photoUrl : String
){
  companion object{
      fun getPhotoSamples() : MutableList<PhotoEpoxy>{
          val photoList = mutableListOf<PhotoEpoxy>()
          for(i in 1..10){
              photoList.add(PhotoEpoxy(title = "Title", photoUrl = "https://images.unsplash.com/photo-1480365501497-199581be0e66?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80"))
          }
          return photoList
      }
  }
}