package easy.guild.wars.streamable;

import java.util.Date;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import easy.guild.wars.domainmodel.Account;
import easy.guild.wars.domainmodel.BasicMemberResult;
import easy.guild.wars.domainmodel.BasicResult;
import easy.guild.wars.domainmodel.Guild;
import easy.guild.wars.streamable.results.EnhancedMemberResult;
import easy.guild.wars.streamable.results.EnhancedResult;
import easy.guild.wars.streamable.results.EnhancedResultContainer;

public class TestStreaming {
	private Guild guild;
	private BasicResult br;
	private EnhancedResult er;
	private EnhancedResultContainer erc;
	
	@Before
	public void setupTest(){
		guild = new Guild();
		guild.name = "420";
		
		br = new BasicResult();
		br.date = new Date();
		br.guild = guild;
		br.rank = (int) Math.floor(5*Math.random());
		
		BasicMemberResult bmr = createBasicMemberResult();
		br.put(bmr.account.name, bmr);
		bmr = createBasicMemberResult();
		br.put(bmr.account.name, bmr);
		
		er = EnhancedResult.enhance(br);
		
		erc = new EnhancedResultContainer();
		erc.add(er);
	}
	
	@Test
	public void testStreaming(){
		System.out.println(GsonProvider.GSON.toJson(br));
		System.out.println(GsonProvider.GSON.toJson(er));
		System.out.println(GsonProvider.GSON.toJson(erc));
	}
	
	private Account createAccount() {
		Account a = new Account();
		a.name = UUID.randomUUID().toString();
		return a;
	}

	private BasicMemberResult createBasicMemberResult(){
		BasicMemberResult bmr = new BasicMemberResult();
		bmr.account = createAccount();
		bmr.might = (int) Math.round(400000d*Math.random());;
		bmr.points = (int) Math.round(4000d*Math.random());
		return bmr;
	}
	
	public void testMemberResult(){
		
	}
	
	
}
