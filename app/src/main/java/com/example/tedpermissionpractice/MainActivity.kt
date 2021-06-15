package com.example.tedpermissionpractice

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        전화 걸기 버튼 누르면 권한 확인 / 전화 연결

        callBtn.setOnClickListener {

//            라이브러리 활용 -> 전화 권한 확인 -> 실제 전화 연결

            val permissionListener = object  : PermissionListener {
                override fun onPermissionGranted() {

//                    권한 승인 -> 실제로 전화 연결 진행

                    val myUri = Uri.parse("tel: 010-0000-0000")
                    val myIntent = Intent(Intent.ACTION_CALL, myUri)

                    startActivity(myIntent)
                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {

//                    권한 거절 -> 토스트로 권한이 없어서 전화 연결 실패

                    Toast.makeText(this@MainActivity, "권한이 없어서 전화 연결에 실패했습니다.", Toast.LENGTH_SHORT).show()

                }

            }

            TedPermission.with(this)
                .setPermissionListener(permissionListener)
                .setDeniedMessage("전화 연결 권한이 필요합니다. 설정에서 진행해주세요.")
                .setPermissions(Manifest.permission.CALL_PHONE)
                .check()

        }

    }
}