package kh.edu.rupp.ite.onlineshop.Ui.Fragment

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.example.UserProfileModel
import com.squareup.picasso.Picasso
import kh.edu.rupp.ite.onlineshop.R
import kh.edu.rupp.ite.onlineshop.Util.API.RetrofitClient
import kh.edu.rupp.ite.onlineshop.Util.API.RetrofitService
import kh.edu.rupp.ite.onlineshop.databinding.FragmentProfileBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ProfileFragment : Fragment() {
    private lateinit var fragmentBinding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var binding = FragmentProfileBinding.bind(view)
        fragmentBinding = binding
        getUserDetail()
    }

    fun getUserDetail(){
        var retrofit = RetrofitService.getRetrofit().create(RetrofitClient::class.java)
        retrofit.getUserProfile().enqueue(object : Callback<UserProfileModel>{
            @RequiresApi(Build.VERSION_CODES.O)
            @SuppressLint("SetTextI18n", "SimpleDateFormat")
            override fun onResponse(
                call: Call<UserProfileModel>,
                response: Response<UserProfileModel>
            ) {
                if (response.isSuccessful){
                    fragmentBinding.fullName.text = "${response.body()?.firstName} ${response.body()!!.lastName}"
                    Picasso.get().load(response.body()!!.image_url).into(fragmentBinding.userProfile)
                    fragmentBinding.childEmail.text = response.body()?.email
                    fragmentBinding.email.text = "${response.body()!!.email}"
                    //format date
                    val date = response.body()!!.birthday.toString()
                    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                    val localDate = LocalDate.parse(date, formatter)
                    val formatter2 = DateTimeFormatter.ofPattern("dd-MMMM-yyyy")
                    val formattedDate = localDate.format(formatter2)
                    fragmentBinding.dateOfBirth.text = formattedDate
                    fragmentBinding.gender.text = "${response.body()!!.gender}"
                    fragmentBinding.phoneNumber.text = response.body()!!.phone_number.toString()
                    fragmentBinding.address.text = response.body()!!.address
                }
                else{
                    Log.d("error", "error in service = ${response.message()}")
                }

            }
            override fun onFailure(call: Call<UserProfileModel>, t: Throwable) {
               Log.d("error", t.message.toString())
            }

        })
    }


}