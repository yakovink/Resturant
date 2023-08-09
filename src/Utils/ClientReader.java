package Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import Exceptions.CommandWasNotFoundException;
import Exceptions.EmptyBufferException;
import Exceptions.ReaderConnectionException;

public class ClientReader extends BufferedReader{

	public ClientReader(Reader in, int sz) {
		super(in, sz);
		// TODO Auto-generated constructor stub
	}

	public ClientReader(Reader in) {
		super(in);
		// TODO Auto-generated constructor stub
	}
	
	public String checkedReadLine() throws EmptyBufferException, CommandWasNotFoundException, ReaderConnectionException {
        String s;
		try {
			s = this.readLine();
		} catch (IOException e) {
			throw new ReaderConnectionException();
		}
        if(s.equals("E")) {
        	throw new EmptyBufferException();
        }
        String[] cm=s.split("/");
        if(cm[0].equals("X")) {
        	throw new CommandWasNotFoundException(cm[1]);
        }
        return s;
    }
	
}
