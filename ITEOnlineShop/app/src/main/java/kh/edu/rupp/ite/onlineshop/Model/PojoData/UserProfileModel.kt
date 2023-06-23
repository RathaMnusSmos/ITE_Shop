package com.example.example

import com.google.gson.annotations.SerializedName


data class UserProfileModel (

  @SerializedName("first_name"   ) var firstName    : String? = null,
  @SerializedName("last_name"    ) var lastName     : String? = null,
  @SerializedName("email"        ) var email        : String? = null,
  @SerializedName("phone-number" ) var phone_number : String? = null,
  @SerializedName("gender"       ) var gender       : String? = null,
  @SerializedName("image-url"    ) var image_url    : String? = null,
  @SerializedName("birthday"     ) var birthday     : String? = null,
  @SerializedName("address"      ) var address      : String? = null

)