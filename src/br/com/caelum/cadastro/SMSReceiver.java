package br.com.caelum.cadastro;

import br.com.caelum.cadastro.dao.AlunoDao;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		Object[] messages = (Object[]) bundle.get("pdus");
		byte[] message = (byte[]) messages[0];
		SmsMessage sms = SmsMessage.createFromPdu(message);
		String address = sms.getDisplayOriginatingAddress();
		
		AlunoDao alunoDao = new AlunoDao(context);
		if (alunoDao.existeAluno(address)) {
			
		}
		
		Toast.makeText(context, "Chegou um SMS de " + address + "!!", Toast.LENGTH_LONG).show();
	}
}
