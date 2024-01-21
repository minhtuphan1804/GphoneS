// const notifications = document.querySelector(".notifications"),
//     buttons = document.querySelectorAll(".buttons .btn");
//
// const toastDetails = {
//     timer: 1500,
//     success: {
//         icon: 'fa-circle-check',
//         text: 'Thanh toán thành công.',
//     },
//     error: {
//         icon: 'fa-circle-xmark',
//         text: 'Error: This is an error toast.',
//     },
//     warning: {
//         icon: 'fa-triangle-exclamation',
//         text: 'Warning: This is a warning toast.',
//     },
//     info: {
//         icon: 'fa-circle-info',
//         text: 'Info: This is an information toast.',
//     }
// }
//
// const removeToast = (toast) => {
//     toast.classList.add("hide");
//     if(toast.timeoutId) clearTimeout(toast.timeoutId); // Clearing the timeout for the toast
//     setTimeout(() => toast.remove(), 1500); // Removing the toast after 500ms
// }
//
// const createToast = (id) => {
//     // Getting the icon and text for the toast based on the id passed
//     const { icon, text } = toastDetails[id];
//     const toast = document.createElement("li"); // Creating a new 'li' element for the toast
//     toast.className = `toast ${id}`; // Setting the classes for the toast
//     // Setting the inner HTML for the toast
//     toast.innerHTML = `<div class="column">
//                          <i class="fa-solid ${icon}"></i>
//                          <span>${text}</span>
//                       </div>
//                       <i class="fa-solid fa-xmark" onclick="removeToast(this.parentElement)"></i>`;
//
//     // Appending the toast to the notification ul
//     notifications.appendChild(toast);
//
//     // Setting a timeout to remove the toast after the specified duration
//     toast.timeoutId = setTimeout(() => {
//         removeToast(toast);
//
//         // Redirecting the user to the specified URL
//         window.location.href = "http://localhost:8080/ban-hang/hien-thi";
//     }, toastDetails.timer);
// }
//
// // Adding a click event listener to each button to create a toast when clicked
// buttons.forEach(btn => {
//     btn.addEventListener("click", () => {
//         // Check if the user confirmed the payment
//         if (confirm('Bạn có muốn thanh toán không ?')) {
//             // Proceed with payment processing
//             createToast(btn.id);
//
//             // Update the payment status to successful
//             // ...
//
//             // Generate and download the PDF file
//             // ...
//         } else {
//             // Payment cancelled
//             // Do nothing
//             return null;
//         }
//     });
// });
