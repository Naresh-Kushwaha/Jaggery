import axios from "axios";

const RazorpayButton = ({totalAmount}) => {

const token=localStorage.getItem("token");


  const createOrder = () => {
    axios.post(
      `http://localhost:8222/api/v1/payment/razorpay/create-order?=${totalAmount}`,
      {
        items: [
          { productId: 1, quantity: 2 },
        ],
        shippingAddress: "Some address",
        totalAmount: totalAmount,
      },
      {
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + token,
        },
      }
    )
    .then((res) => {
      console.log(res.data);
      const data = res.data;
     
      const order = {
        id: data.id,
        amount: data.amount,
      };
      startPayment(data, token, order);
    })
    .catch((err) => {
      console.error(err);
      alert("Failed to create order");
    });
  };

  const startPayment = (data, token, order) => {
    const options = {
      key: "rzp_test_F1EGRQAup4QNOA",
      amount: data.amount * 100,
      currency: data.currency,
      order_id: data.id,
      handler: function (response) {
        console.log(response);
        axios.post(
  "http://localhost:8222/api/v1/payment/razorpay/verify",
  {
    razorpay_payment_id: response.razorpay_payment_id,
    razorpay_order_id: response.razorpay_order_id,
    razorpay_signature: response.razorpay_signature,
  },
  {
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + token,
    },
  }
        )
        .then((res) => {
          alert("Payment Verified!");
          console.log("Verification Response:", res.data);
          console.log("Order:", order);
        })
        .catch((err) => {
          console.error(err);
          alert("Verification Failed");
        });
      },
      theme: {
        color: "#3399cc",
      },
    };

    const rzp = new window.Razorpay(options);
    rzp.open();
  };

  return (
    <div className="p-4">
      <button
        onClick={createOrder}
        className="px-4 py-2 bg-green-600 text-white rounded"
      >
        Pay Now
      </button>
    </div>
  );
};

export default RazorpayButton;