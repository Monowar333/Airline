package ua.nure.korkh.SummaryTask4.web.command;

import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import ua.nure.korkh.SummaryTask4.web.command.admin.AddFlight;
import ua.nure.korkh.SummaryTask4.web.command.admin.AddPlane;
import ua.nure.korkh.SummaryTask4.web.command.admin.AddUser;
import ua.nure.korkh.SummaryTask4.web.command.admin.AdminCabinet;
import ua.nure.korkh.SummaryTask4.web.command.admin.ClaimList;
import ua.nure.korkh.SummaryTask4.web.command.admin.DeleteFligts;
import ua.nure.korkh.SummaryTask4.web.command.admin.DeletePlane;
import ua.nure.korkh.SummaryTask4.web.command.admin.DeleteUser;
import ua.nure.korkh.SummaryTask4.web.command.admin.FlightList;
import ua.nure.korkh.SummaryTask4.web.command.admin.PlaneList;
import ua.nure.korkh.SummaryTask4.web.command.admin.SaveUser;
import ua.nure.korkh.SummaryTask4.web.command.admin.UpdateFlight;
import ua.nure.korkh.SummaryTask4.web.command.admin.UpdatePlane;
import ua.nure.korkh.SummaryTask4.web.command.admin.UpdateUser;
import ua.nure.korkh.SummaryTask4.web.command.admin.WatchBrigadeAdmin;
import ua.nure.korkh.SummaryTask4.web.command.brigade.BrigadeCabinet;
import ua.nure.korkh.SummaryTask4.web.command.common.LogoutCommand;
import ua.nure.korkh.SummaryTask4.web.command.common.NoCommand;
import ua.nure.korkh.SummaryTask4.web.command.dispatcher.AddClaim;
import ua.nure.korkh.SummaryTask4.web.command.dispatcher.ChangeStatusFlight;
import ua.nure.korkh.SummaryTask4.web.command.dispatcher.DeleteBrigade;
import ua.nure.korkh.SummaryTask4.web.command.dispatcher.DispatcherCabinet;
import ua.nure.korkh.SummaryTask4.web.command.dispatcher.MyListClaims;
import ua.nure.korkh.SummaryTask4.web.command.dispatcher.WatchBrigade;
import ua.nure.korkh.SummaryTask4.web.command.dispatcher.WorkWithAirport;

/**
 * Holder for all commands.<br/>
 * 
 * @author Korkh
 * 
 */
public class CommandContainer {

	private static final Logger LOG = Logger.getLogger(CommandContainer.class);

	private static Map<String, Command> commands = new TreeMap<String, Command>();

	static {
		// common commands
		commands.put("login", new LoginCommand());
		commands.put("logout", new LogoutCommand());
		commands.put("editLanguage", new EditLanguageCommand());
		commands.put("noCommand", new NoCommand());
		commands.put("activeaccount", new ActiveAccount());

		// administrator commands
		commands.put("admincabinet", new AdminCabinet());
		commands.put("deleteuser", new DeleteUser());
		commands.put("updateuser", new UpdateUser());
		commands.put("claimlistadmin", new ClaimList());
		commands.put("adduser", new AddUser());
		commands.put("saveuser", new SaveUser());
		commands.put("flightlist", new FlightList());
		commands.put("addflight", new AddFlight());
		commands.put("saveflight", new AddFlight());
		commands.put("updateflight", new UpdateFlight());
		commands.put("saveupdateflight", new UpdateFlight());
		commands.put("deleteflights", new DeleteFligts());
		commands.put("changestatusclaim", new ClaimList());
		commands.put("watchbrigadeadmin", new WatchBrigadeAdmin());
		commands.put("planelist", new PlaneList());
		commands.put("addplane", new AddPlane());
		commands.put("savenewplane", new AddPlane());
		commands.put("updateplane", new UpdatePlane());
		commands.put("saveupdateplane", new UpdatePlane());
		commands.put("deleteplane", new DeletePlane());

		// dispatcher commands
		commands.put("dispatchercabinet", new DispatcherCabinet());
		commands.put("watchbrigade", new WatchBrigade());
		commands.put("saveflightbrigade", new WatchBrigade());
		commands.put("changestatusflight", new ChangeStatusFlight());
		commands.put("savenewclaim", new AddClaim());
		commands.put("addclaim", new AddClaim());
		commands.put("mylistclaims", new MyListClaims());
		commands.put("listairport", new WorkWithAirport());
		commands.put("changestatusairport", new WorkWithAirport());
		commands.put("deletebrigade", new DeleteBrigade());
		
		//brigade commands
		commands.put("brigadecabinet", new BrigadeCabinet());
		
		LOG.debug("Command container was successfully initialized");
		LOG.trace("Number of commands --> " + commands.size());
	}

	/**
	 * Returns command object with the given name.
	 * 
	 * @param commandName
	 *            Name of the command.
	 * @return Command object.
	 */
	public static Command get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			LOG.trace("Command not found, name --> " + commandName);
			return commands.get("noCommand");
		}

		return commands.get(commandName);
	}

}