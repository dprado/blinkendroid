package org.cbase.blinkendroid.network;

import java.util.StringTokenizer;

import org.cbase.blinkendroid.Constants;

import android.util.Log;


public class BlinkendroidProtocolHandler implements ICommandHandler {
    BlinkendroidListener playerListener;
    public BlinkendroidProtocolHandler(BlinkendroidListener playerListener) {
	this.playerListener=playerListener;
    }

    public void handle(byte[] data) {
	String input = new String(data);
	if (input.startsWith("T"))
	    playerListener.serverTime(Long.parseLong(input.substring(1)));
	else if (input.startsWith("C")) {
	    // clipping "Cstartx,starty,endx,endy"
	    StringTokenizer tokenizer = new StringTokenizer(input.substring(1),
		    ",");
	    float startX = Float.parseFloat(tokenizer.nextToken());
	    float startY = Float.parseFloat(tokenizer.nextToken());
	    float endX = Float.parseFloat(tokenizer.nextToken());
	    float endY = Float.parseFloat(tokenizer.nextToken());
	    playerListener.clip(startX, startY, endX, endY);
	}else if (input.startsWith("P")) {
	    // clipping "Cstartx,starty,endx,endy"
	    StringTokenizer tokenizer = new StringTokenizer(input.substring(1),
		    ",");
	    int x = Integer.parseInt(tokenizer.nextToken());
	    int y = Integer.parseInt(tokenizer.nextToken());
	    int resId = Integer.parseInt(tokenizer.nextToken());
	    long serverTime = Long.parseLong(tokenizer.nextToken());
	    long startTime = Long.parseLong(tokenizer.nextToken());
	    Log.i(Constants.LOG_TAG,"Play "+x+","+y);
	    playerListener.play(resId, serverTime, startTime);
	}

    }
}
