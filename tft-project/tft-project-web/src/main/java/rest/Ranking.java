package rest;
import java.util.List;

import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import rest.RankRep;
import Modele.Rank;

/**
 * JAX-RS Example
 * <p/>
 * This class produces a RESTful service to read/write the contents of the
 * rank table.
 */
@Path("/ranking")
@RequestScoped
@Stateful
public class Ranking {

		@Inject
		private RankRep repository;

		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<Rank> listRank() {
			return repository.displayRank();
		}

	}