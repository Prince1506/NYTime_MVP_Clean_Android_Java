package com.mvp_clean.ny_times_articles.core.view.activities;


import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.mvp_clean.ny_times_articles.R;
import com.mvp_clean.ny_times_articles.core.callback.IRetryCallBack;
import com.mvp_clean.ny_times_articles.core.view.IBaseView;

public class BaseActivity extends AppCompatActivity implements IBaseView {
    private KProgressHUD progressDialog;


    @Override
    public void  startServerApiErrorScreen(String error) {

       new AlertDialog.Builder(this)
            .setTitle(getString(R.string.error))
            .setCancelable(false)
            .setMessage(error)
            .setPositiveButton(getString(R.string.ok), (dialog, which) -> {
                dialog.dismiss();
                finish();
            })
            .show();
    }

    public Boolean isNetworkAvailable(final Context context) {
        try {
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager != null) {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                return activeNetworkInfo != null && activeNetworkInfo.isConnected();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
     public void   startServerApiRetryScreen(String error, IRetryCallBack context) {
        new AlertDialog.Builder(this)
            .setTitle(getString(R.string.error))
            .setCancelable(false)
            .setMessage(error)
            .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                    finish();

                }
            })
            .show();
    }

    public void showProgress() {
        try {
            progressDialog = KProgressHUD.create(this)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setLabel("   " + getString(R.string.loading) + "   ")
                    .setCancellable(false)
                    .setAnimationSpeed(2)
                    .setDimAmount(0.5f)
                    .show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hideProgress() {
        try {
            if (progressDialog != null) {
                progressDialog.dismiss();
                progressDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}