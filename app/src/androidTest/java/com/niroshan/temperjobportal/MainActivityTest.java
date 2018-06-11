package com.niroshan.temperjobportal;

import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;
import io.reactivex.Observable;
import com.niroshan.temperjobportal.config.AppConstants;
import com.niroshan.temperjobportal.model.BeanJobListResponse;
import com.niroshan.temperjobportal.retrofit.ApiInterface;
import android.test.suitebuilder.annotation.SmallTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import io.reactivex.android.schedulers.AndroidSchedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest extends InstrumentationTestCase {

    private MockRetrofit mockRetrofit;
    private Retrofit retrofit;

      @Test
    public void useAppContext() throws Exception {

          retrofit = new Retrofit.Builder().baseUrl(AppConstants.BASE_URL)
                  .client(new OkHttpClient())
                  .addConverterFactory(GsonConverterFactory.create())
                  .build();

          NetworkBehavior behavior = NetworkBehavior.create();

          mockRetrofit = new MockRetrofit.Builder(retrofit)
                  .networkBehavior(behavior)
                  .build();
    }

    @SmallTest
    public void testRandomJobsRetrieval() throws Exception {
        BehaviorDelegate<ApiInterface> delegate = mockRetrofit.create(ApiInterface.class);
        ApiInterface mockJobService = new MockJobSearchService(delegate);

        //Actual Test
        Observable<BeanJobListResponse> jobs = mockJobService.fetchJobList(AppConstants.MAIN_URL);
        Observable<BeanJobListResponse> jobResponse = jobs.observeOn(AndroidSchedulers.mainThread());

        //Asserting response
        assertTrue(jobResponse.subscribe().isDisposed());
        assertEquals("Sample Test Case", jobResponse.subscribe().toString());

    }

    @SmallTest
    public void testFailedQuoteRetrieval() throws Exception {
        BehaviorDelegate<ApiInterface> delegate = mockRetrofit.create(ApiInterface.class);
        MockFailedJobService mockJobService = new MockFailedJobService(delegate);

        Observable<BeanJobListResponse> jobs = mockJobService.fetchJobList(AppConstants.MAIN_URL);
        Observable<BeanJobListResponse> jobResponse = jobs.observeOn(AndroidSchedulers.mainThread());

        assertFalse(jobResponse.subscribe().isDisposed());

        //Asserting response
        assertEquals(404, jobResponse.subscribe());
        assertEquals("Jobs Not Found", "Something Wrong");

    }
}
