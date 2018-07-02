package com.lisarathfelder.mynotes.client;
import com.googlecode.gwt.crypto.bouncycastle.DataLengthException;
import com.googlecode.gwt.crypto.bouncycastle.InvalidCipherTextException;
import com.googlecode.gwt.crypto.client.TripleDesCipher;
import com.googlecode.gwt.crypto.client.TripleDesKeyGenerator;


public class Crypto {
    
	private TripleDesCipher encryptor;  

	public Crypto() {
		    TripleDesKeyGenerator generator = new TripleDesKeyGenerator();
		    byte[] key = generator.decodeKey("04578a8f0be3a7109d9e5e86839e3bc41654927034df92ec"); //you can pass your own string here

		    //initializing encryptor with generated key
		    encryptor = new TripleDesCipher();
		    encryptor.setKey(key);
	}
	
	
	  public String encryptString(String string)
	    {
	        try 
	        {
	            string = encryptor.encrypt( string );
	        } 
	        catch (DataLengthException e1) 
	        {
	            e1.printStackTrace();
	        } 
	        catch (IllegalStateException e1) 
	        {
	            e1.printStackTrace();
	        } 
	        catch (InvalidCipherTextException e1) 
	        {
	            e1.printStackTrace();
	        }

	        return string;
	    }

	    public String decryptString(String string)
	    {
	        try 
	        {
	            string = encryptor.decrypt(string);
	        } 
	        catch (DataLengthException e) 
	        {
	            e.printStackTrace();
	        } catch (IllegalStateException e) 
	        {
	            e.printStackTrace();
	        } catch (InvalidCipherTextException e)
	        {
	            e.printStackTrace();
	        }

	        return string;
	    }
	
	

}
