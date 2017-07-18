
<nav class="navbar navbar-inverse" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/">Index</a>
		</div>

		<div class="navbar-collapse collapse" id="main-navbar">
			<ul class="nav navbar-nav navbar-right">

				<sec:authorize access="!hasRole('ROLE_ADMIN') and !hasRole('USER')">
					<a href="registry">registry</a>
				</sec:authorize>
				<a href="hello">hello</a>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
				<a href="add">dodaj produkt</a>
				<%--  --%></sec:authorize>
				<sec:authorize access="hasRole('ROLE_ADMIN') or hasRole('USER')">
					<a href="list">lista produktow</a>
					<a href="myshopping">moje zakupy</a>
				</sec:authorize>


			</ul>
		</div>
	</div>
</nav>
