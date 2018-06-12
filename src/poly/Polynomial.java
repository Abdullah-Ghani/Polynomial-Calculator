package poly;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class implements evaluate, add and multiply for polynomials.
 *
 * @author runb-cs112
 *
 */
public class Polynomial {

	/**
	 * Reads a polynomial from an input stream (file or keyboard). The storage format
	 * of the polynomial is:
	 * <pre>
	 *     <coeff> <degree>
	 *     <coeff> <degree>
	 *     ...
	 *     <coeff> <degree>
	 * </pre>
	 * with the guarantee that degrees will be in descending order. For example:
	 * <pre>
	 *      4 5
	 *     -2 3
	 *      2 1
	 *      3 0
	 * </pre>
	 * which represents the polynomial:
	 * <pre>
	 *      4*x^5 - 2*x^3 + 2*x + 3 
	 * </pre>
	 *
	 * @param sc Scanner from which a polynomial is to be read
	 * @throws IOException If there is any input error in reading the polynomial
	 * @return The polynomial linked list (front node) constructed from coefficients and
	 *         degrees read from scanner
	 */
	public static Node read(Scanner sc)
			throws IOException {
		Node poly = null;
		while (sc.hasNextLine()) {
			Scanner scLine = new Scanner(sc.nextLine());
			poly = new Node(scLine.nextFloat(), scLine.nextInt(), poly);
			scLine.close();
		}
		return poly;
	}

	/**
	 * Returns the sum of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 *
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list
	 * @return A new polynomial which is the sum of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node add(Node poly1, Node poly2) {
		/** COMPLETE THIS METHOD **/
		Node first=null;
		Node last=null;
		while(poly1!=null || poly2!=null){
			if (poly1==null) {
				Node poly3=new Node(poly2.term.coeff,poly2.term.degree,null);
				if (first==null) {
					first=poly3;
					last=poly3;
				}else {
					last.next=poly3;
					last=poly3;
				}
				poly2=poly2.next;
			}
			else if  (poly2==null) {
				Node poly3=new Node(poly1.term.coeff,poly1.term.degree,null);
				if (first==null) {
					first=poly3;
					last=poly3;
				}else {
					last.next=poly3;
					last=poly3;
				}
				poly1=poly1.next;
			}


			else if (poly1.term.degree<poly2.term.degree) {
				Node poly3=new Node(poly1.term.coeff,poly1.term.degree,null);
				if (first==null) {
					first=poly3;
					last=poly3;
				}else {
					last.next=poly3;
					last=poly3;
				}
				poly1=poly1.next;
			}
			else if (poly2.term.degree<poly1.term.degree) {
				Node poly3=new Node(poly2.term.coeff,poly2.term.degree,null);
				if (first==null) {
					first=poly3;
					last=poly3;
				}else {

					last.next=poly3;
					last=poly3;
				}
				poly2=poly2.next;
			}
			else if (poly1.term.degree==poly2.term.degree) {
				Node poly3= new Node(poly1.term.coeff+poly2.term.coeff,poly1.term.degree,null);
				if (poly3.term.coeff==0) {
					poly3=null;

				}
				else if(first==null) {
					first=poly3;
					last=poly3;
				}else{
					last.next=poly3;
					last=poly3;
				}
				poly1=poly1.next;
				poly2=poly2.next;
			}


		}
		return first;
	}

	/**
	 * Returns the product of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 *
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list)
	 * @return A new polynomial which is the product of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node multiply(Node poly1, Node poly2) {
		/** COMPLETE THIS METHOD **/
		Node outer;
		Node inner;
		Node temp=null;
		if (poly2==null) {
			return null;
		}
		if (poly1==null) {
			return null;
		}
		for (outer=poly1;outer!=null;outer=outer.next) {
			Node First=null;
			Node Last=null;
			for (inner =poly2;inner!=null;inner=inner.next) {
				Node poly3= new Node(outer.term.coeff*inner.term.coeff,outer.term.degree+inner.term.degree,null);
				if (First==null) {
					First=poly3;
					Last=poly3;}

				else if(First!=null) {
					Last.next=poly3;
					Last=poly3;}

			}
			if (outer==poly1) {
				temp= First;
			}
			else {
				temp=add(temp,First);
			}

		}
		return temp;}



	/**
	 * Evaluates a polynomial at a given value.
	 *
	 * @param poly Polynomial (front of linked list) to be evaluated
	 * @param x Value at which evaluation is to be done
	 * @return Value of polynomial p at x
	 */
	public static float evaluate(Node poly, float x) {
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
		float X=0;
		for (Node current=poly;current!=null;current=current.next) {
			double a=Math.pow(x,current.term.degree);
			float b= (float) (current.term.coeff*a);
			X+=b;

		}
		return X;
	}

	/**
	 * Returns string representation of a polynomial
	 *
	 * @param poly Polynomial (front of linked list)
	 * @return String representation, in descending order of degrees
	 */
	public static String toString(Node poly) {
		if (poly == null) {
			return "0";
		}

		String retval = poly.term.toString();
		for (Node current = poly.next ; current != null ;
			 current = current.next) {
			retval = current.term.toString() + " + " + retval;
		}
		return retval;
	}
}