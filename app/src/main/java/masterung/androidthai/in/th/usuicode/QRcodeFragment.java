package masterung.androidthai.in.th.usuicode;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QRcodeFragment extends Fragment implements ZXingScannerView.ResultHandler{

    private ZXingScannerView zXingScannerView;
    private String resultString;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        zXingScannerView = new ZXingScannerView(getActivity());

        return zXingScannerView;
    }   // onCreateView

    @Override
    public void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();
    }

    @Override
    public void onResume() {
        super.onResume();

        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();

    }

    @Override
    public void handleResult(Result result) {

        resultString = result.toString().trim();
        if (!resultString.isEmpty()) {

//            resultString ==> Value
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contentServiceFragment, new ServiceFragment())
                    .commit();

        }   // if

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                zXingScannerView.resumeCameraPreview(QRcodeFragment.this);
            }
        }, 2000);



    }   // handleResult
}   // Main Class
