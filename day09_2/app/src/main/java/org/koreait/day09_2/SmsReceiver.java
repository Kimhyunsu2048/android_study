package org.koreait.day09_2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        SmsMessage[] smsMessages = parseSmsMessage(bundle);
        // -> 문자함에 있는 전체 메세지 -> 가장 첫번째 메세지 0번째 - 방금 수신된 문자
        if (smsMessages != null && smsMessages.length > 0) {
            SmsMessage message = smsMessages[0]; // 방금 수신된 메세지
            String sender = message.getOriginatingAddress(); // 수신 휴대폰 번호
            String content = message.getMessageBody(); // 수신 메세지
            Log.i("SMS_RECEIVER", "보낸이 : " + sender);
            Log.i("SMS_RECEIVER", "수신메세지 : " + content);

            Intent intent2 = new Intent(context, SmsViewActivity2.class);
            intent2.putExtra("sender", sender);
            intent2.putExtra("content", content);

            intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

            context.startActivity(intent2);
        }
    }

    private SmsMessage[] parseSmsMessage(Bundle bundle) {
        // pdus - 문자 데이터 전달 키값
        Object[] objs = (Object[]) bundle.get("pdus");
        SmsMessage[] smsMessages = new SmsMessage[objs.length];
        // SmsMessage.createFromPdu
        for (int i = 0; i < objs.length; i++) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // 마시멜로 버전 이후(23)
                String format = bundle.getString("format");
                smsMessages[i] = SmsMessage.createFromPdu((byte[])objs[i], format);
            } else { // 23 이전
                smsMessages[i] = SmsMessage.createFromPdu((byte[])objs[i]);
            }
        }

        return smsMessages;

    }
}