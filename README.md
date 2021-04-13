## App components

Zoom SDK v.5.4.3.613

## Description of the audio fix

Zoom SDK uses the native library for fetching microphones as builtin, microphone in earphones or bluetooth devices. Android TVs don't have these types of microphone and use usb-camera. So when user need audio, we off speakerphones and it allows TV to record and share audio from microphone in usb-camera.

```java
 setVolumeControlStream(AudioManager.STREAM_VOICE_CALL);
 am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
 am.setSpeakerphoneOn(false);
```
This code you can find in MyMeetingActivity.java file on 756-758 lines.

## Audio format used by Zoom

Zoom uses AudioTrack for managing and playing audio in Android. Also Zoom used next parameters:

1) Mono audio format;
2) One channel for audio;
3) 48,000 Hz sample rate;
4) PCM 16 Bit Audio Codec

## Zoom Token

Zoom uses JWT (JSON Web Token) for initialization ZoomSdk. For creating JWT Zoom uses custom Header and Payload according to [example here](https://marketplace.zoom.us/docs/sdk/native-sdks/auth). This JWT may exist max 2 days, so for each launching generate new JWT in method
 ```java
private String getJwtToken()
```
inside InitAuthSDKHelper.java file during 2 days (from 1 day ago to tomorrow). 
Also JWT may be static in AuthConstants.java file:
```java
public final static String SDK_JWTTOKEN = ""
```  

## Customizing

At the moment app doesn't have specific style for different views. So each view should customize separately. Layout of main screen is in 
```bash
init_auth_sdk.xml
```
For changing Sagemcom logo or background on Main screen should replace next files in drawable package
```bash
sagemcomlogo.png
sagemcom_device_background.png
```
