package com.ezen.demo.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeUtility;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MailReader
{
   String pop3Host = "";
   String userName = "";
   String password = "";

   public void readEmail()
   {
       //Set properties
       Properties props = new Properties();
       props.put("mail.store.protocol", "");
       props.put("mail.pop3.host", );
       props.put("mail.pop3.port", "");
       props.put("mail.pop3.starttls.enable", "");
    
       // Get the Session object.
       Session session = Session.getInstance(props);
    
       try {
           //Create the POP3 store object and connect to the pop store.
      Store store = session.getStore("pop3s");
      store.connect(pop3Host, userName, password);
    
      //폴더 이름 확인하기
      Folder root = store.getDefaultFolder();
      Folder[] folders = root.list();
      for(int i=0;i<folders.length;i++) {
         log.info("{}.{}", i+1, folders[i].getFullName());  // INBOX라는 이름의 메일박스가 있는 것
      }

      //Create the folder object and open it in your mailbox.
      Folder emailFolder = store.getFolder("INBOX"); // Folder를 다룰 수 있는 기능과 속성 준비
      
      if (emailFolder == null) {
            throw new Exception("Invalid folder");
        } // 자바에는 무조건 빈 박스가 있기 때문에 여기서 오류날 경우는 없음
        // try to open read/write and if that fails try read-only
        try {
           emailFolder.open(Folder.READ_WRITE); // 쓰기 권한이 있어야지 삭제도 가능
           log.info("읽고 쓰기 모드");
        } catch (MessagingException ex) {
           emailFolder.open(Folder.READ_ONLY);
           log.info("읽기 모드");
        }
    
        int totalCnt = emailFolder.getMessageCount();
        
        log.info("총 메일 수 : {}", totalCnt);
        
      //Retrieve the messages from the folder object.
      Message[] messages = emailFolder.getMessages();
      log.info("가져온 메일의 수 : {}", messages.length);
    
      //Iterate the messages
      for (int i=0; i < totalCnt; i++) { // 총 메일수만큼 반복
         if((i%10) != 0) continue; // 1/10만 보기
         
         //Message message = emailFolder.getMessage(i);
         Message message = messages[i]; // 1개만 꺼내기
         /*
         Calendar cal = Calendar.getInstance();
         cal.set(2022, 0,1);
         Date date = cal.getTime();
         if(message.getSentDate().before(date)) continue;
         */
         Address[] toAddress = // 수신자를 List로 저장
                message.getRecipients(Message.RecipientType.TO); // Recipients : 수신자
            log.info("---------------------------------");  
            log.info("Details of Email Message " + (i + 1) + " :");
            
            // 메일의 제목 가져오기
            String subject = message.getSubject();
            //제목의 한글이 깨지면 프로젝트 > 마우스 우측 > Properties > Resources > Text File Encoding 항목 설정 변경
           
            log.info("Subject: {}", subject);  
            
            //깨진 한글열을 복호화(decoding)
            String from = MimeUtility.decodeText(message.getFrom()[0].toString());
            log.info("From: {}", from);  
    
            //Iterate recipients 
            for(int j = 0; j < toAddress.length; j++){
            // 수신자의 주소를 출력
               String to = MimeUtility.decodeText(toAddress[j].toString());
               log.info("To: {}", to);
            }
           
            log.info("보낸 날짜 : {}", message.getSentDate());
           
            log.info("Conetnt-Type : {}", message.getContentType());
           
            try {  // 텍스트 메시지인 경우
               String contents = (String)message.getContent();
              log.info("텍스트 메시지={}",contents);
              continue;
           }catch(ClassCastException cce) { // 여기서 에러가 났다는건 String만 있는 메일이 아닌 첨부파일도 있다는 것
              log.info("첨부파일을 가진 메일");
           }
           
           //Iterate multiparts
           Multipart multipart = (Multipart) message.getContent();
           
           for(int k = 0; k < multipart.getCount(); k++){ // multipart안의 bodypart의 수

              BodyPart bodyPart = multipart.getBodyPart(k); // bodypart 하나씩 가져오기
              
              String bodyContentType = bodyPart.getContentType();
              log.info("바디파트 content-type : {}", bodyContentType);
              
              if (bodyPart.isMimeType("text/*")) //첨부파일을 가진 바디파트, 멀티파트 안에 있는 텍스트 파트
            	  								 //text/로 시작하는 프로그램
              {
                 String strContent = bodyPart.getContent().toString();
                 log.info("텍스트 바디파트 : {}", strContent);
                 continue;
              }
              else // 첨부파일이 들어간 파일, text만 있었으면 142Line continue에서 끝났어야 함
              {
                 String fname = bodyPart.getFileName();
                 if(fname==null || fname.equals("")) continue;
                 
                 fname = MimeUtility.decodeText(fname);
                 log.info("첨부파일 이름 : {}", fname);
                 
                 // bodypart에서 Stream을 지원함!!!!!!!!
                 // 메모리를 이용해서 성능을 올리는 것
                 // 읽어오는 것
                 BufferedInputStream bin = new BufferedInputStream(bodyPart.getInputStream()); // 입력stream이 bin
                 byte[] buf = new byte[1024*10];
                 
                 BufferedOutputStream bout = null;
                 try { // 출력 스트림 ~162
                    bout = new BufferedOutputStream(new FileOutputStream(fname)); // 구글에서 가져와서 내 스트림에 저장하겠다
                 }catch(FileNotFoundException fne) {
                    continue;
                 }
                 
                 // 여기서부터는 연결해주는 것
                 int read = 0;
                 // 읽어온 양이 read
                 while((read=bin.read(buf, 0, buf.length)) != -1) // bin(inputstream)으로부터 읽어서 buf에 저장, 0부터 buf.length만큼
                 {
                	// outputstream bout
                    bout.write(buf, 0, read); // 파일에다가(내 시스템에다가) 쓴다. read는 1024*10이 꽉 채우지 못함
                 }
                 bout.close();
                 bin.close();
                 log.info("파일 저장 성공({})", fname);
              }

             log.info("\n");  
            } //outer for() */

         }
    
         //close the folder and store objects
         emailFolder.close(false);
         store.close();
      } catch (NoSuchProviderException e) {
         e.printStackTrace();
      } catch (MessagingException e){
         e.printStackTrace();
      } catch (Exception e) {
             e.printStackTrace();
      }
    
   }
   
   public void deleteMail(Message message) {
      try {
         int msgNum = message.getMessageNumber();
         message.setFlag(Flags.Flag.DELETED, true);
         log.info("메일 삭제 성공 :{}", msgNum);
         
      } catch (MessagingException e) {
         e.printStackTrace();
      }
   }
   
   public boolean removeMail(int num) 
   {
       //Set properties
       Properties props = new Properties();
       props.put("mail.store.protocol", "pop3");
       props.put("mail.pop3.host", pop3Host);
       props.put("mail.pop3.port", "995");
       props.put("mail.pop3.starttls.enable", "true");
    
       // Get the Session object.
       Session session = Session.getInstance(props);
    
       try {
           //Create the POP3 store object and connect to the pop store.
         Store store = session.getStore("pop3s");
         store.connect(pop3Host, userName, password);

         Folder emailFolder = store.getFolder("INBOX");

           try {
              emailFolder.open(Folder.READ_WRITE);
              log.info("읽고 쓰기 모드");
           } catch (MessagingException ex) {
              emailFolder.open(Folder.READ_ONLY);
              log.info("읽기 모드");
           }

         Message[] messages = emailFolder.getMessages();
         
         messages[num].setFlag(Flags.Flag.DELETED, true);
         log.info("메일 삭제 성공 :{}", num);
         
         return true;
       }catch(Exception ex) {
          ex.printStackTrace();
       }
      return false;
   }
}
