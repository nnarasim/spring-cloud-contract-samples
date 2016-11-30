package contracts.beer.rest

org.springframework.cloud.contract.spec.Contract.make {
	request {
		description("""
Represents a successful scenario of getting a beer

given:
	client is old enough
when:
	he applies for a beer
then:
	we'll grant him the beer
""")
		method 'POST'
		url '/check'
		body(
				age: $(c(regex('[2-9][0-9]')))
		)
		headers {
			contentType(applicationJson())
		}
	}
	response {
		status 200
		body( """
			{
				"status": "OK"
			}
			""")
		headers {
			contentType(applicationJson())
		}
	}
}
