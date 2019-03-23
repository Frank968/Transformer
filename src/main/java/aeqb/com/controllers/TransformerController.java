package aeqb.com.controllers;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import aeqb.com.beans.Transformer;
import aeqb.com.beans.TransformerResponse;
import aeqb.com.beans.TransformerRegistration;


@Controller 
public class TransformerController {
	@RequestMapping(method = RequestMethod.POST, value="/transformer", produces = "application/json")
	@ResponseBody
	public TransformerResponse registerTransformer(@RequestBody Transformer trans) {
		System.out.println("In registerTransformer "+trans.toString());        
		return TransformerRegistration.getInstance().addTransformer(trans);
	}
	  
	@RequestMapping(method = RequestMethod.PUT, value="/transformer/{regNum}", produces = "application/json")
	@ResponseBody
	public TransformerResponse updateTransformerRecord(@PathVariable("regNum") int regNum, @RequestBody Transformer trans) {
		System.out.println("In updateTransformerRecord "+trans.toString());   
		return TransformerRegistration.getInstance().updateTransformer(regNum,trans);
	}

	@RequestMapping(method = RequestMethod.DELETE, value="/transformer/{regNum}", produces = "application/json")
	@ResponseBody
	public TransformerResponse deleteStudentRecord(@PathVariable("regNum") int regNum) {
		System.out.println("In deleteStudentRecord "+regNum);   
		return TransformerRegistration.getInstance().deleteTransformer(regNum);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/transformer", produces = "application/json")	
	@ResponseBody
	public List<Transformer> getAllTransformers() {
		return TransformerRegistration.getInstance().getTransformerRecords();
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/transformer/winningteam", produces = "application/json")	
	@ResponseBody
	public TransformerResponse getTransformersWiningteam() {
		return TransformerRegistration.getInstance().getWinningTeam();
	}		
}
