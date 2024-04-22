document.addEventListener("DOMContentLoaded", () => {
	const elements = {
		authForm: document.getElementById("authForm"),
		transferForm: document.getElementById("transferForm"),
		topUpButton: document.getElementById("topUpButton"),
		walletAddress: document.getElementById("walletAddress"),
		walletBalance: document.getElementById("walletBalance"),
		loginButton: document.getElementById("loginButton"),
		refreshBalanceButton: document.getElementById("refreshBalance"),
		signupButton: document.getElementById("signupButton"),
		user: document.getElementById("user"),
		password: document.getElementById("password"),
		amount: document.getElementById("amount"),
		recipient: document.getElementById("recipient"),
		authSection: document.getElementById("authSection"),
		walletSection: document.getElementById("walletSection"),
	};

	let jwtToken = null;
	let walletBalance = 0;

	function toggleUI(isLoggedIn) {
		elements.authSection.style.display = isLoggedIn ? "none" : "block";
		elements.walletSection.style.display = isLoggedIn ? "block" : "none";
	}

	function handleApiResponse(res) {
		if (!res.ok) {
			return res.json().then(err => {
				throw new Error(err.message);
			});
		}
		return res.json();
	}

	function handleError(error) {
		toastr.error(error);
	}

	function updateWalletInfo(wallet) {
		const newWalletBalance = parseFloat(wallet.balance);
		if (walletBalance && newWalletBalance > walletBalance) {
			toastr.success("Incoming funds transfer");
		}
		walletBalance = newWalletBalance
		elements.walletBalance.textContent = `$${walletBalance.toFixed(2)}`;
		elements.walletAddress.value = wallet.address;
	}

	elements.authForm.addEventListener("submit", e => {
		e.preventDefault();
		const { user, password, signupButton } = elements;
		const endpoint = e.submitter === signupButton ? '/auth/signup' : '/auth/login';
		const body = JSON.stringify({ user: user.value, password: password.value });

		fetch(endpoint, {
			method: 'POST',
			headers: { "Content-Type": "application/json" },
			body
		})
			.then(handleApiResponse)
			.then(data => {
				if (data.token) {
					jwtToken = data.token;
					toastr.info('Logged in successfully!');
					toggleUI(true);
					fetchWalletBalance();
					fetchUserName();
				} else {
					toastr.error("Invalid Server Response");
				}
			})
			.catch(handleError);
	});

	function fetchUserName() {
		fetch('/user/me', { headers: { "Authorization": `Bearer ${jwtToken}` } })
			.then(handleApiResponse)
			.then(data => {
				toastr.info(`Welcome, <b>${data.name}</b>!`);
			})
			.catch(handleError);
	}

	function fetchWalletBalance() {
		if (jwtToken == null) {
			return;
		}
		fetch('/wallet/balance', { headers: { "Authorization": `Bearer ${jwtToken}` } })
			.then(handleApiResponse)
			.then(data => {
				updateWalletInfo(data);
			})
			.catch(handleError);
	}

	elements.refreshBalanceButton.addEventListener("click", fetchWalletBalance);

	elements.topUpButton.addEventListener("click", () => {
		fetch('/wallet/topup', { headers: { "Authorization": `Bearer ${jwtToken}` } })
			.then(handleApiResponse)
			.then(data => {
				toastr.success("Your wallet has been topped up!");
				updateWalletInfo(data);
			})
			.catch(handleError);
	});

	elements.transferForm.addEventListener("submit", e => {
		e.preventDefault();
		const { amount, recipient } = elements;
		const body = JSON.stringify({ to: recipient.value, amount: parseFloat(amount.value) });

		fetch('/wallet/transfer', {
			method: 'POST',
			headers: {
				"Content-Type": "application/json",
				"Authorization": `Bearer ${jwtToken}`
			},
			body
		})
			.then(handleApiResponse)
			.then(data => {
				toastr.success("Money transferred successfully!");
				updateWalletInfo(data);
			})
			.catch(handleError);
	});

	toggleUI(false);
});
