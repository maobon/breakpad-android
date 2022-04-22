package fun.learnlife.breakpaddemo;

import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.sample.breakpad.BreakpadInit;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private File externalReportPath;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("crash-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initExternalReportPath();

        initBreakPad();

        // Example of a call to a native method
        TextView tv = findViewById(R.id.text);
        tv.setOnClickListener(view -> crash());
    }

    private void initBreakPad() {
        String path = externalReportPath.getAbsolutePath();
        BreakpadInit.initBreakpad(path);
    }


    private void initExternalReportPath() {
        // externalReportPath = new File(Environment.getExternalStorageDirectory(), "crashDump");
        externalReportPath = new File(getFilesDir(), "crashDump");
        if (!externalReportPath.exists()) {
            boolean ret = externalReportPath.mkdirs();
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native void crash();
}
