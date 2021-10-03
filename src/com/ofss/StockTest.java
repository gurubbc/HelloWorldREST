package com.ofss;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("stock")
public class StockTest {

	
	public static ArrayList<Stock> allStocks=new ArrayList<Stock>();
	
	static
	{
		
		System.out.println("static block is called and initializing the data");
		Stock s1=new Stock(100, "OFSS", 999); // Java Format
		Stock s2=new Stock(101, "IBM", 899); // Java Format
		Stock s3=new Stock(102, "WIPRO", 299); // Java Format
		Stock s4=new Stock(103, "INFOSYS", 599); // Java Format
		Stock s5=new Stock(104, "CTS", 699); // Java Format
		
		
		
		allStocks.add(s1);
		allStocks.add(s2);
		allStocks.add(s3);
		allStocks.add(s4);
		allStocks.add(s5);

	}
	
	@DELETE
	@Path("delete/{stockId}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteAStock(@PathParam("stockId") int sid)
	{
		// Find out the stock based on id
		boolean isStockFound=false;
		
		for (Stock exStock:allStocks)
		{
			if (exStock.getStockId()==sid)
			{
				isStockFound=true;
				System.out.println("Found the stock in the list");
				allStocks.remove(exStock); // remvoing existing stock
				break; // come out of the for loop
				
			}
		} // end of for loop
		
		if (isStockFound==true)
		{
			return "Stock Deteld Successfully"; // real msg	
		}
		
		else
		{
			return "Not Found the stock in the list, so nothing got deleted";
		}
		
		
	}
	
	
	@PUT
	@Path("modify/{stockId}") //path parameter
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateAStock(@PathParam("stockId") int sid, Stock stObj)
	{
		System.out.println("Received the stock id as "+sid);
		
		boolean isStockFound=false;
		
		for (Stock exStock:allStocks)
		{
			if (exStock.getStockId()==sid)
			{
				isStockFound=true;
				System.out.println("Found the stock in the list");
				
				if (stObj.getStockName()!=null && stObj.getStockPrice()!=0)
				{
					System.out.println("SO, YOU WANT TO MODIFY BOTH");
					exStock.setStockPrice(stObj.getStockPrice());
					exStock.setStockName(stObj.getStockName());
				}
				else
				if (stObj.getStockPrice()!=0)
				{
					// user wants to modify the stock price
					System.out.println("YOU WANT TO MODIFY STOCK PRICE");
					exStock.setStockPrice(stObj.getStockPrice());
				}
				else
					if (stObj.getStockName()!=null)
					{
						
						// user wants to modify the stock name
						System.out.println("YOU WANT TO MODIFY STOCK NAME");
						exStock.setStockName(stObj.getStockName());
					}

				
				break;
			}
		} // end of for loop
		
		if (isStockFound==true)
		{
			return "Stock Updated Successfully"; // real msg	
		}
		
		else
		{
			return "Not Found the stock in the list, so nothing got updated";
		}
		
	}
	
	@POST
	@Path("add")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String addAStock(Stock stObject)
	{
		
		System.out.println("before add, length of allStocks is "+allStocks.size());
		// From the arraylist, you invoke add method to add this stock object to existing allStocks
		allStocks.add(stObject);
		System.out.println("after add, length of allStocks is "+allStocks.size());
		return "The stock has been successfully added";
	}
	
	
	@GET
	
	@Produces(MediaType.APPLICATION_JSON)
	@Path("json")
	public Stock getASingleStock1()
	{
		Stock s1=new Stock(100, "OFSS", 999); // Java Format
		return s1;
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("xml")
	public Stock getASingleStock2()
	{
		Stock s1=new Stock(100, "OFSS", 999); // Java Format
		return s1;
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("allstocksxml")
	public ArrayList<Stock> getAllStocks()
	{
		if (allStocks.size()>0)
			return allStocks; // returning the arraylist
			// here I need to write DB programming (select * from stock)
		else
			return null;
		
	}
	
	@GET
	@Path("{stockname}") // path parameter
	@Produces(MediaType.APPLICATION_XML)
	public Stock getMeOneStock(@PathParam("stockname") String sname)
	{
		
		// Now, I need to write a searching logic
		// based on this sid, i can search the stock and return that object now
		Stock s=new Stock(); // empty stock
		
		for (Stock ss:allStocks)
		{
			if (ss.getStockName().equalsIgnoreCase(sname))
			{
				s=ss;
			}
		}
		return s;
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("allstocksjson")
	public ArrayList<Stock> getAllStocksJson()
	{
		return allStocks;
		
	}
	

}
