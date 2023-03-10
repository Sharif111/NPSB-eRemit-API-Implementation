package com.ibank.ibanking.npsb.action;

import com.ibank.ibanking.npsb.bo.NPSBEremitTransactionStatusUpdateBO;
import com.ibank.ibanking.npsb.dao.NPSBEremitTransactionStatusUpdateDAO;
import com.ibank.ibanking.npsb.formbean.NPSBEremitTransactionStatusUpdateForm;
import com.ibank.utility.RemoveNullValue;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class NPSBEremitTransactionStatusUpdateAction extends Action {
   public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
      new NPSBEremitTransactionStatusUpdateBO();
      NPSBEremitTransactionStatusUpdateBO oNPSBEremitTransactionStatusUpdateBO = new NPSBEremitTransactionStatusUpdateBO();
      NPSBEremitTransactionStatusUpdateDAO oNPSBEremitTransactionStatusUpdateDAO = new NPSBEremitTransactionStatusUpdateDAO();
      NPSBEremitTransactionStatusUpdateForm oNPSBEremitTransactionStatusUpdateForm = (NPSBEremitTransactionStatusUpdateForm)form;
      new NPSBEremitTransactionStatusUpdateBO();
      RemoveNullValue oRemoveNullValue = new RemoveNullValue();
      oRemoveNullValue.removeNullValue(oNPSBEremitTransactionStatusUpdateForm);
      String sActionPath = "";
      sActionPath = mapping.getPath();
      HttpSession session = request.getSession(true);
      String sSuccessAction = "success";
      String sFailureAction = "failure";
      String sFatalErrorAction = "fatalError";
      String sSessionExpireAction = "sessionExpire";
      String sSessionMyBankMenuAction = "sessionMyBankMenu";
      String sSuccess = sFatalErrorAction;
      String sActionPathName = "";
      String gsUserID = (String)session.getAttribute("GSUserID");
      String gsUserTitle = (String)session.getAttribute("GSUserTitle");
      String gsLastLogInDate = (String)session.getAttribute("GSLastLogInDate");
      String gsLogInUserID = (String)session.getAttribute("GSLogInUserID");
      String gsSessionID = (String)session.getAttribute("GSSessionID");
      String gsInternalCardID = (String)session.getAttribute("GSInternalCardID");
      String gsHeaderName = (String)session.getAttribute("GSHeaderName");
      String gsHeaderLogIn = (String)session.getAttribute("GSHeaderLogIn");
      String gsCompanyID = (String)session.getAttribute("GSCompanyCode");
      String gsBranchID = (String)session.getAttribute("GSBranchCode");
      String gsBranchName = (String)session.getAttribute("GSBranchName");
      String gsTellerID = (String)session.getAttribute("GSTellerID");
      String gsCompanyName = (String)session.getAttribute("GSCompanyName");
      String gsBranchOpenDateDDFormat = (String)session.getAttribute("GSBranchOpenDateDDFormat");
      String sBranchActionPathName = "/ibanking/nPSBEremitTransactionStatusUpdate.do";
      String NPSBOutgoingReversalMessageBO="";
      if (sActionPath.equals("/nPSBEremitTransactionStatusUpdate")) {
         session.setAttribute("oNPSBOutgoingReversalMessageBO", " ");
         this.clearForm(oNPSBEremitTransactionStatusUpdateForm);
         sSuccess = sSuccessAction;
      } else if (sActionPath.equals("/eremitDetailsOfNPSBTransactionStatusUpdate")) {
         session.setAttribute("oNPSBOutgoingReversalMessageBO", " ");
         session.setAttribute("oNPSBEremitTransactionStatusUpdateBO", (Object)null);
         oNPSBEremitTransactionStatusUpdateForm.setFromDate(gsBranchOpenDateDDFormat);
         oNPSBEremitTransactionStatusUpdateForm.setToDate(gsBranchOpenDateDDFormat);
         sSuccess = sSuccessAction;
      } else {
        
        // String NPSBOutgoingReversalMessageBO;
         if (sActionPath.equals("/searchNPSBEremitTransactionStatusUpdate")) {
            session.setAttribute("oNPSBOutgoingReversalMessageBO", " ");
            session.setAttribute("oNPSBEremitTransactionStatusUpdateBO", null);
            
            oNPSBEremitTransactionStatusUpdateBO.setFromDate(oNPSBEremitTransactionStatusUpdateForm.getFromDate()); 
            oNPSBEremitTransactionStatusUpdateBO.setToDate(oNPSBEremitTransactionStatusUpdateForm.getToDate());
            
           
            
            oNPSBEremitTransactionStatusUpdateBO = oNPSBEremitTransactionStatusUpdateDAO.getNPSBeRemitAPI(oNPSBEremitTransactionStatusUpdateBO);
            
            
        	System.out.println("Transaction action B=> " + oNPSBEremitTransactionStatusUpdateBO.getKeyId());
            if (oNPSBEremitTransactionStatusUpdateBO.getErrorCode().equals("0")) { 
            	
            	oNPSBEremitTransactionStatusUpdateBO.setFromDate(oNPSBEremitTransactionStatusUpdateForm.getFromDate()); 
                oNPSBEremitTransactionStatusUpdateBO.setToDate(oNPSBEremitTransactionStatusUpdateForm.getToDate());
                
                System.out.println("Transaction action from=> " + oNPSBEremitTransactionStatusUpdateForm.getFromDate());
                System.out.println("Transaction action to data=> " + oNPSBEremitTransactionStatusUpdateForm.getToDate());
                
            	//System.out.println("Token B=> " + oNPSBEremitTransactionStatusUpdateBO.getKeyId());
            	oNPSBEremitTransactionStatusUpdateBO = oNPSBEremitTransactionStatusUpdateDAO.responseNPSBeRemitAPI(oNPSBEremitTransactionStatusUpdateBO);
            	     	 
            	 
             // NPSBOutgoingReversalMessageBO = oNPSBEremitTransactionStatusUpdateBO.getErrorMessage();
              session.setAttribute("oNPSBEremitTransactionStatusUpdateBO", oNPSBEremitTransactionStatusUpdateBO);
             // session.setAttribute("oNPSBEremitTransactionStatusUpdateBO", oNPSBEremitTransactionStatusUpdateBO);
              // System.out.println("errorCode A =>" + oNPSBEremitTransactionStatusUpdateBO.getErrorCode());
              // System.out.println("Transaction Amount B=> " + oNPSBEremitTransactionStatusUpdateBO.getTransactionAmount());
               
               sSuccess = sSuccessAction;
            } else if (oNPSBEremitTransactionStatusUpdateBO.getErrorCode().equals("1")) {
               NPSBOutgoingReversalMessageBO = oNPSBEremitTransactionStatusUpdateBO.getErrorMessage();
               session.setAttribute("oNPSBOutgoingReversalMessageBO", NPSBOutgoingReversalMessageBO);
               sSuccess = sFailureAction;
            } else if (oNPSBEremitTransactionStatusUpdateBO.getErrorCode().equals("2")) {
               this.clearSession(session);
               sSuccess = sSessionExpireAction;
            } else if (oNPSBEremitTransactionStatusUpdateBO.getErrorCode().equals("3")) {
               this.clearSession(session);
               sSuccess = sSessionMyBankMenuAction;
            } else {
               this.clearSession(session);
               sSuccess = sFatalErrorAction;
            }
         }
            if (sActionPath.equals("/executeNPSBEremitTransactionStatusUpdate")) {
                session.setAttribute("oNPSBOutgoingReversalMessageBO", " ");
                session.setAttribute("oNPSBEremitTransactionStatusUpdateBO", null);
                oNPSBEremitTransactionStatusUpdateBO = oNPSBEremitTransactionStatusUpdateDAO.getNPSBeRemitAPI(oNPSBEremitTransactionStatusUpdateBO);
                
                             
                oNPSBEremitTransactionStatusUpdateBO.setMailId(gsUserID);
                oNPSBEremitTransactionStatusUpdateBO.setSessionId(gsSessionID);
                
                oNPSBEremitTransactionStatusUpdateBO.setTransactionID(oNPSBEremitTransactionStatusUpdateForm.getTransactionID()); 
                oNPSBEremitTransactionStatusUpdateBO.setStatus(oNPSBEremitTransactionStatusUpdateForm.getStatus());
                oNPSBEremitTransactionStatusUpdateBO.setMessage(oNPSBEremitTransactionStatusUpdateForm.getMessage());
                
                
                oNPSBEremitTransactionStatusUpdateBO.setErrorMessage(oNPSBEremitTransactionStatusUpdateForm.getErrorMessage());                                                                           
                System.out.println("errorCode 555 =>" + oNPSBEremitTransactionStatusUpdateBO.getErrorCode());
                System.out.println("errorMessage  555 => " + oNPSBEremitTransactionStatusUpdateBO.getErrorMessage());
                         
            	//System.out.println("token 555=> " + oNPSBEremitTransactionStatusUpdateBO.getKeyId());
                
                oNPSBEremitTransactionStatusUpdateBO = oNPSBEremitTransactionStatusUpdateDAO.updateNPSBeRemitAPI(oNPSBEremitTransactionStatusUpdateBO);
                
                if (oNPSBEremitTransactionStatusUpdateBO.getErrorCode().equals("0")) {               	               	
                	
                    System.out.println("errorCode 666 =>" + oNPSBEremitTransactionStatusUpdateBO.getErrorCode());
                    System.out.println("errorMessage 666 => " + oNPSBEremitTransactionStatusUpdateBO.getErrorMessage());                  
                    NPSBOutgoingReversalMessageBO = oNPSBEremitTransactionStatusUpdateBO.getErrorMessage();
                    session.setAttribute("oNPSBOutgoingReversalMessageBO", NPSBOutgoingReversalMessageBO);
                    session.setAttribute("oNPSBEremitTransactionStatusUpdateBO", oNPSBEremitTransactionStatusUpdateBO);
                     sSuccess = sSuccessAction;                                   	            	                	
                  
                } else if (oNPSBEremitTransactionStatusUpdateBO.getErrorCode().equals("1")) {
                   NPSBOutgoingReversalMessageBO = oNPSBEremitTransactionStatusUpdateBO.getErrorMessage();
                   session.setAttribute("oNPSBOutgoingReversalMessageBO", NPSBOutgoingReversalMessageBO);
                   sSuccess = sFailureAction;
                } else if (oNPSBEremitTransactionStatusUpdateBO.getErrorCode().equals("2")) {
                   this.clearSession(session);
                   sSuccess = sSessionExpireAction;
                } else if (oNPSBEremitTransactionStatusUpdateBO.getErrorCode().equals("3")) {
                   this.clearSession(session);
                   sSuccess = sSessionMyBankMenuAction;
                } else {
                   this.clearSession(session);
                   sSuccess = sFatalErrorAction;
                }
            } else if (sActionPath.equals("/cancelNPSBEremitTransactionStatusUpdate")) {
            session.setAttribute("oNPSBOutgoingReversalMessageBO", (Object)null);
            oNPSBEremitTransactionStatusUpdateBO = oNPSBEremitTransactionStatusUpdateDAO.getMenuCheckPro(gsUserID, gsSessionID, gsCompanyID, gsBranchID, request.getRemoteAddr(), sBranchActionPathName);
            if (oNPSBEremitTransactionStatusUpdateBO.getErrorCode().equals("0")) {
               this.clearSession(session);
               NPSBOutgoingReversalMessageBO = oNPSBEremitTransactionStatusUpdateBO.getErrorMessage();
               session.setAttribute("oNPSBOutgoingReversalMessageBO", NPSBOutgoingReversalMessageBO);
               sSuccess = sSuccessAction;
            } else if (oNPSBEremitTransactionStatusUpdateBO.getErrorCode().equals("1")) {
               NPSBOutgoingReversalMessageBO = oNPSBEremitTransactionStatusUpdateBO.getErrorMessage();
               session.setAttribute("oNPSBOutgoingReversalMessageBO", NPSBOutgoingReversalMessageBO);
               sSuccess = sFailureAction;
            } else if (oNPSBEremitTransactionStatusUpdateBO.getErrorCode().equals("2")) {
               this.clearSession(session);
               sSuccess = sSessionExpireAction;
            } else if (oNPSBEremitTransactionStatusUpdateBO.getErrorCode().equals("3")) {
               this.clearSession(session);
               sSuccess = sSessionMyBankMenuAction;
            } else {
               this.clearSession(session);
               sSuccess = sFatalErrorAction;
            }
         }
      }

      return mapping.findForward(sSuccess);
   }

   public void populateMenu(NPSBEremitTransactionStatusUpdateForm oNPSBEremitTransactionStatusUpdateForm, NPSBEremitTransactionStatusUpdateBO oNPSBEremitTransactionStatusUpdateBO) {
      oNPSBEremitTransactionStatusUpdateForm.setMenuList(oNPSBEremitTransactionStatusUpdateBO.getMenuList());
      oNPSBEremitTransactionStatusUpdateForm.setMenuNameList(oNPSBEremitTransactionStatusUpdateBO.getMenuNameList());
   }

   public void populateMessage(NPSBEremitTransactionStatusUpdateForm oNPSBEremitTransactionStatusUpdateForm, NPSBEremitTransactionStatusUpdateBO oNPSBEremitTransactionStatusUpdateBO) {
      oNPSBEremitTransactionStatusUpdateForm.setList(oNPSBEremitTransactionStatusUpdateBO.getList());
   }

   private void clearForm(NPSBEremitTransactionStatusUpdateForm oNPSBEremitTransactionStatusUpdateForm) {
     
      oNPSBEremitTransactionStatusUpdateForm.setTransactionID("");   
      oNPSBEremitTransactionStatusUpdateForm.setStatus("");
      oNPSBEremitTransactionStatusUpdateForm.setTransactionAmount("");
      
   }

   private void clearSession(HttpSession session) {
      session.setAttribute("oNPSBEremitTransactionStatusUpdateBO", " ");
      session.setAttribute("oNPSBOutgoingReversalMessageBO", " ");
   }
}

