package Modele;

	
	import java.io.Serializable;
	import java.math.BigDecimal;

	import javax.persistence.Entity;
	import javax.persistence.Id;
	import javax.persistence.Table;
	import javax.validation.constraints.NotNull;
	import javax.xml.bind.annotation.XmlRootElement;


	@Entity
	@XmlRootElement
	@Table(name = "rank")
	public class Rank implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Id
		private int rank;

		@NotNull
		private String player;

		@NotNull
		private BigDecimal points;

		/**
		 * @return the rank
		 */
		public int getRank() {
			return rank;
		}

		/**
		 * @param rank
		 *            the rank to set
		 */
		public void setRank(int rank) {
			this.rank = rank;
		}

		/**
		 * @return the player
		 */
		public String getPlayer() {
			return player;
		}

		/**
		 * @param player
		 *            the player to set
		 */
		public void setPlayer(String player) {
			this.player = player;
		}

		/**
		 * @return the points
		 */
		public BigDecimal getPoints() {
			return points;
		}

		/**
		 * @param points
		 *            the points to set
		 */
		public void setPoints(BigDecimal points) {
			this.points = points;
		}

	}


