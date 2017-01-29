package ua.nure.korkh.SummaryTask4.web.command;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.korkh.SummaryTask4.bean.FlightsBean;

public class FlightsListSort {

	private static final Logger LOG = Logger.getLogger(FlightsListSort.class);
	private static Comparator<FlightsBean> compareById = new CompareById();
	private static Comparator<FlightsBean> compareByFromCity = new CompareByFromCity();
	private static Comparator<FlightsBean> compareByFromCountry = new CompareByFromCountry();
	private static Comparator<FlightsBean> compareByFromName = new CompareByFromName();
	private static Comparator<FlightsBean> compareByWhereCity = new CompareByWhereCity();
	private static Comparator<FlightsBean> compareByWhereCountry = new CompareByWhereCountry();
	private static Comparator<FlightsBean> compareByWhereName = new CompareByWhereName();
	private static Comparator<FlightsBean> compareByNumber = new CompareByNumber();
	private static Comparator<FlightsBean> compareByStatus = new CompareByStatus();
	private static Comparator<FlightsBean> compareByDate = new CompareByDate();
	private static Comparator<FlightsBean> compareByPlain = new CompareByPlain();

	private static class CompareByPlain implements Comparator<FlightsBean>,
			Serializable {
		private static final long serialVersionUID = 1L;

		public int compare(FlightsBean bean1, FlightsBean bean2) {
			if(bean1.getIdplains() != null && bean2.getIdplains() != null){
			return bean1.getIdplains().compareTo(bean2.getIdplains());}
			if(bean1.getIdplains() == null){
				return 1;
			}
			return 0;
		}
	}

	private static class CompareByDate implements Comparator<FlightsBean>,
			Serializable {
		private static final long serialVersionUID = 1L;

		public int compare(FlightsBean bean1, FlightsBean bean2) {
			return bean1.getDeparturedate().compareTo(bean2.getDeparturedate());
		}
	}

	private static class CompareByStatus implements Comparator<FlightsBean>,
			Serializable {
		private static final long serialVersionUID = 1L;

		public int compare(FlightsBean bean1, FlightsBean bean2) {
			return bean1.getStatus().compareTo(bean2.getStatus());
		}
	}

	private static class CompareByNumber implements Comparator<FlightsBean>,
			Serializable {
		private static final long serialVersionUID = 1L;

		public int compare(FlightsBean bean1, FlightsBean bean2) {
			return bean1.getNumber().compareTo(bean2.getNumber());
		}
	}

	private static class CompareByFromCity implements Comparator<FlightsBean>,
			Serializable {
		private static final long serialVersionUID = 1L;

		public int compare(FlightsBean bean1, FlightsBean bean2) {
			return bean1.getFromwhenceCity().compareTo(
					bean2.getFromwhenceCity());
		}
	}

	private static class CompareByFromCountry implements
			Comparator<FlightsBean>, Serializable {
		private static final long serialVersionUID = 1L;

		public int compare(FlightsBean bean1, FlightsBean bean2) {
			return bean1.getFromwhenceCountry().compareTo(
					bean2.getFromwhenceCountry());
		}
	}

	private static class CompareByFromName implements Comparator<FlightsBean>,
			Serializable {
		private static final long serialVersionUID = 1L;

		public int compare(FlightsBean bean1, FlightsBean bean2) {
			return bean1.getFromwhenceName().compareTo(
					bean2.getFromwhenceName());
		}
	}

	private static class CompareByWhereCity implements Comparator<FlightsBean>,
			Serializable {
		private static final long serialVersionUID = 1L;

		public int compare(FlightsBean bean1, FlightsBean bean2) {
			return bean1.getWhereCity().compareTo(bean2.getWhereCity());
		}
	}

	private static class CompareByWhereCountry implements
			Comparator<FlightsBean>, Serializable {
		private static final long serialVersionUID = 1L;

		public int compare(FlightsBean bean1, FlightsBean bean2) {
			return bean1.getWhereCountry().compareTo(bean2.getWhereCountry());
		}
	}

	private static class CompareByWhereName implements Comparator<FlightsBean>,
			Serializable {
		private static final long serialVersionUID = 1L;

		public int compare(FlightsBean bean1, FlightsBean bean2) {
			return bean1.getWhereName().compareTo(bean2.getWhereName());
		}
	}

	private static class CompareById implements Comparator<FlightsBean>,
			Serializable {
		private static final long serialVersionUID = 1L;

		public int compare(FlightsBean bean1, FlightsBean bean2) {
			if (bean1.getId() < bean2.getId()) {
				return -1;
			} else {
				return 1;
			}
		}
	}

	public static List<FlightsBean> sortFlightsBean(String sort,
			List<FlightsBean> beanlist) {
		switch (sort) {
		case "byid":
			Collections.sort(beanlist, compareById);
			break;
		case "byfromcity":
			Collections.sort(beanlist, compareByFromCity);
			break;
		case "byfromcountry":
			Collections.sort(beanlist, compareByFromCountry);
			break;
		case "byfromname":
			Collections.sort(beanlist, compareByFromName);
			break;
		case "bywherecity":
			Collections.sort(beanlist, compareByWhereCity);
			break;
		case "bywherecountry":
			Collections.sort(beanlist, compareByWhereCountry);
			break;
		case "bywherename":
			Collections.sort(beanlist, compareByWhereName);
			break;
		case "number":
			Collections.sort(beanlist, compareByNumber);
			break;
		case "departuredate":
			Collections.sort(beanlist, compareByDate);
			break;
		case "plains":
			Collections.sort(beanlist, compareByPlain);
			break;
		case "status":
			Collections.sort(beanlist, compareByStatus);
			break;
		}
		LOG.debug("SORT BY" + sort);
		return beanlist;
	}
}
