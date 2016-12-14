package chapter1;

import java.util.Enumeration;
import java.util.Vector;

public class Movie {
	public static final int CHILDREN = 2;
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;
	
	private String mTitle;
	private int mPriceCode;
	
	public Movie(String mTitle, int mPriceCode) {
		this.mTitle = mTitle;
		this.mPriceCode = mPriceCode;
	}
	
	public int getPriceCode() {
		return mPriceCode;
	}
	
	public void setPriceCode(int mPriceCode) {
		this.mPriceCode = mPriceCode;
	}
	public String getTitle() {
		return mTitle;
	}
}
class Rental{//表示一个乘客租了一部影片
	private Movie mMovie;
	private int mDayRented;
	public Rental(Movie movie, int dayRented) {
		this.mMovie = movie;
		this.mDayRented = dayRented;
	}
	public Movie getMovie() {
		return mMovie;
	}
	public int getDayRented() {
		return mDayRented;
	}
}

class Customer{
	private String name;
	private Vector mRentalsVector = new Vector();

	public void addRental(Rental rental) {
		mRentalsVector.addElement(rental);
	}
	public String getName() {
		return name;
	}
	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;//常客积点
		
		Enumeration rentals = mRentalsVector.elements();
		
		String result = "Rental Racord for " + getName() + "\n";
		while(rentals.hasMoreElements()){
			double thisAmount = 0;
			Rental each = (Rental)rentals.nextElement();//取得一笔租借记
			switch (each.getMovie().getPriceCode()) {
			case Movie.REGULAR:
				thisAmount+=2;
				if (each.getDayRented()>2) {
					thisAmount += (each.getDayRented() - 2 ) * 1.5 ;
				}
				break;
			case Movie.CHILDREN:
				thisAmount += 1.5;
				if (each.getDayRented()>3) {
					thisAmount += (each.getDayRented() - 3 ) * 1.5 ;
				}
				break;
			case Movie.NEW_RELEASE:
				thisAmount += each.getDayRented() * 3;
				
			default:
				break;
			}
			frequentRenterPoints++;
			if (each.getMovie().getPriceCode() == Movie.NEW_RELEASE && each.getDayRented() > 1) {
				frequentRenterPoints++;
			}
			result += '\t' + each.getMovie().getTitle() + "\t" + thisAmount + "\n";
			totalAmount += thisAmount;
		}
		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "You earned " + frequentRenterPoints + "frequent render points";
		return result;
	}
	
}