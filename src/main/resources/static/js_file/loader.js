// /js_file/loader.js — final

// We support either: <div class="loader-overlay loader">...</div>
// or <div class="loader">...</div> (your Thymeleaf adds both classes to the same node)
const GLOBAL_SELECTORS  = ['.loader-overlay.loader', '.loader', '.loading'];
const CHECKOUT_SELECTORS = ['.loader-overlay.loading-checkout', '.loading-checkout'];

let hideTimer;

const $ = (s) => document.querySelector(s);
const $$ = (s) => Array.from(document.querySelectorAll(s));

function pick(selectors) {
    for (const s of selectors) {
        const el = $(s);
        if (el) return el;
    }
    return null;
}

// Safety: remove duplicates if any (keep first)
function dedupe(selectors) {
    const nodes = selectors.flatMap(s => $$(s));
    nodes.forEach((el, i) => {
        if (i > 0) el.remove();
    });
}

function show(el) {
    if (!el) return;
    el.style.display = 'flex';
    el.classList.remove('loader--hidden');
}
function hide(el) {
    if (!el) return;
    clearTimeout(hideTimer);
    hideTimer = setTimeout(() => {
        el.classList.add('loader--hidden');
        el.addEventListener('transitionend', () => {
            el.style.display = 'none';
        }, { once: true });
    }, 120);
}

// Public API (global page loader)
export function showLoader()       { show(pick(GLOBAL_SELECTORS)); }
export function hideLoader()       { hide(pick(GLOBAL_SELECTORS)); }
// Optional API (checkout-only overlay)
export function showCheckoutLoader(){ show(pick(CHECKOUT_SELECTORS)); }
export function hideCheckoutLoader(){ hide(pick(CHECKOUT_SELECTORS)); }

// --- Boot sequence ---
document.addEventListener('DOMContentLoaded', () => {
    // 1) Dedupe overlays (just in case)
    dedupe([GLOBAL_SELECTORS, CHECKOUT_SELECTORS].flat());

    // 2) Ensure checkout overlay is hidden (even if CSS was wrong earlier)
    const checkout = pick(CHECKOUT_SELECTORS);
    if (checkout) {
        checkout.style.display = 'none';
        checkout.classList.add('loader--hidden');
    }

    // 3) Show the global loader during boot if present
    const global = pick(GLOBAL_SELECTORS);
    if (global) show(global);
}, { once: true });

window.addEventListener('load', () => {
    // Hide the global loader once everything is loaded
    hide(pick(GLOBAL_SELECTORS));
}, { once: true });

// Keep loader visible when navigating away (nice UX)
window.addEventListener('beforeunload', () => {
    const global = pick(GLOBAL_SELECTORS);
    if (global) {
        global.style.display = 'flex';
        global.classList.remove('loader--hidden');
    }
});
//loading