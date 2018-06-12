package com.niroshan.temperjobportal.services;

import android.app.Service;
import android.content.Intent;
import android.location.Criteria;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Niroshan Rathnayake on 6/12/2018.
 */

public class LocationService extends Service implements LocationListener, GpsStatus.Listener {

    public static final String TAG = LocationService.class.getSimpleName();

    private final LocationServiceBinder binder = new LocationServiceBinder();
    boolean isLocationManagerUpdatingLocation;

    ArrayList<Location> locationList;
    boolean isLogging;

    public LocationService() {
    }

    @Override
    public void onCreate() {
        isLocationManagerUpdatingLocation = false;
        locationList = new ArrayList<>();
        isLogging = false;
    }

    public void startLogging(){
        isLogging = true;
    }

    public void stopLogging(){
        isLogging = false;
    }

    @Override
    public int onStartCommand(Intent i, int flags, int startId) {
        super.onStartCommand(i, flags, startId);
        return Service.START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind ");
        return true;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy ");
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(TAG, "onRebind ");
    }

    //This is where we detect the app is being killed, thus stop service.
    @Override
    public void onTaskRemoved(Intent rootIntent) {
        if (this.isLocationManagerUpdatingLocation == true) {
            this.stopUpdatingLocation();
            isLocationManagerUpdatingLocation = false;
        }

        stopSelf();
    }

    @Override
    public void onGpsStatusChanged(int event) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        if (provider.equals(LocationManager.GPS_PROVIDER)) {
            if (status == LocationProvider.OUT_OF_SERVICE) {
                notifyLocationProviderStatusUpdated(false);
            } else {
                notifyLocationProviderStatusUpdated(true);
            }
        }
    }

    @Override
    public void onProviderEnabled(String provider) {
        if (provider.equals(LocationManager.GPS_PROVIDER)) {
            notifyLocationProviderStatusUpdated(true);
        }
    }

    @Override
    public void onProviderDisabled(String provider) {
        if (provider.equals(LocationManager.GPS_PROVIDER)) {
            notifyLocationProviderStatusUpdated(false);
        }
    }

    private void notifyLocationProviderStatusUpdated(boolean isLocationProviderAvailable) {
        //Broadcast location provider status change here
    }

    public void startUpdatingLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        //Exception thrown when GPS or Network provider were not available on the user's device.
        try {
            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_FINE);
            criteria.setPowerRequirement(Criteria.POWER_HIGH);
            criteria.setAltitudeRequired(false);
            criteria.setSpeedRequired(true);
            criteria.setCostAllowed(true);
            criteria.setBearingRequired(false);

            //API level 9 and up
            criteria.setHorizontalAccuracy(Criteria.ACCURACY_HIGH);
            criteria.setVerticalAccuracy(Criteria.ACCURACY_HIGH);
            //criteria.setBearingAccuracy(Criteria.ACCURACY_HIGH);
            //criteria.setSpeedAccuracy(Criteria.ACCURACY_HIGH);

            Integer gpsFreqInMillis = 1000;
            Integer gpsFreqInDistance = 1;  // in meters

            locationManager.addGpsStatusListener(this);

            locationManager.requestLocationUpdates(gpsFreqInMillis, gpsFreqInDistance, criteria, this, null);

        } catch (IllegalArgumentException e) {
            Log.e(TAG, e.getLocalizedMessage());
        } catch (SecurityException e) {
            Log.e(TAG, e.getLocalizedMessage());
        } catch (RuntimeException e) {
            Log.e(TAG, e.getLocalizedMessage());
        }
    }

    public void stopUpdatingLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(final Location newLocation) {
        Log.d(TAG, "(" + newLocation.getLatitude() + "," + newLocation.getLongitude() + ")");

        if(isLogging){
            locationList.add(newLocation);
        }

        Intent intent = new Intent("LocationUpdated");
        intent.putExtra("location", newLocation);
        LocalBroadcastManager.getInstance(this.getApplication()).sendBroadcast(intent);
    }

    private long getLocationAge(Location newLocation) {
        long locationAge;
        if (android.os.Build.VERSION.SDK_INT >= 17) {
            long currentTimeInMilli = (long) (SystemClock.elapsedRealtimeNanos() / 1000000);
            long locationTimeInMilli = (long) (newLocation.getElapsedRealtimeNanos() / 1000000);
            locationAge = currentTimeInMilli - locationTimeInMilli;
        } else {
            locationAge = System.currentTimeMillis() - newLocation.getTime();
        }
        return locationAge;
    }

    public class LocationServiceBinder extends Binder {
        public LocationService getService() {
            return LocationService.this;
        }
    }
}
